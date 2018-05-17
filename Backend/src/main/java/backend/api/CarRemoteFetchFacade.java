/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.api;

import backend.exceptions.NotFoundException;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stanislav
 */
public class CarRemoteFetchFacade {
    
    Gson gson = new Gson();
    String[] urls = {
        "https://stanitech.dk/carrentalapi/api/cars",
        "http://www.ramsbone.dk:8085/api/cars"
//        "https://www.devrancoskun.dk/CarRental-backend/api/cars"
    };
    
    
    

    public CarRemoteFetchFacade() {
    }

    private String fetch(String[] urls) {
        ExecutorService es = Executors.newFixedThreadPool(10);
        List<Future<String>> futures = new ArrayList<Future<String>>();
        List<String> cars = new ArrayList<>();
        for (int i = 0; i < urls.length; i++) {
            final int n = i;
            Callable<String> c1 = (() -> {
            //TODO tråde til at hente fra alle URL'er
                try {
                    URL address = new URL(urls[n]);
                    HttpURLConnection conn = (HttpURLConnection) address.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setRequestProperty("User-Agent", "server");
                    Scanner scan = new Scanner(conn.getInputStream());
                    String jsonStr = "";
                    while (scan.hasNext()) {
                        jsonStr += scan.nextLine();
                    }
                    scan.close();
                    if(jsonStr.length() < 30)
                        return null;
                    jsonStr = jsonStr.replaceAll("\\s","");
                    String js = jsonStr.substring(9, jsonStr.length()-2);
                    return js;
                }
                catch (Exception ex) {
                    Logger.getLogger(CarRemoteFetchFacade.class.getName()).log(Level.SEVERE, null, ex + "::: Something went wrong"
                            + " with this URL: " + urls[n]);
                    return null;
                }
            });
            if(c1 != null) {
                Future<String> future = es.submit(c1);
                futures.add(future);
            }
        }
        for(Future<String> fut : futures){
            try {
                cars.add(fut.get());
            } catch (Exception ex) {
                Logger.getLogger(CarRemoteFetchFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        StringBuilder carsJSON = new StringBuilder();
        System.out.println(cars.size());
        cars.forEach((car) -> {
            carsJSON.append(car + ",");
        });
        String temp = carsJSON.substring(0, carsJSON.length()-1);
        temp = "{ \"Cars\": [" + temp + "]}";
        return temp;
    }
    
    public String fetchSpecificCar(String url) {
        try {
            URL address = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) address.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("User-Agent", "server");
            Scanner scan = new Scanner(conn.getInputStream());
            String jsonStr = "";
            while (scan.hasNext()) {
                jsonStr += scan.nextLine();
            }
            scan.close();
            return jsonStr;
        }
        catch (Exception ex) {
            throw new NotFoundException("Unable to connect");
        }
    }
    
    private String put(String url, String message) {

        try {
            URL address = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) address.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);

            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("User-Agent", "server");
            conn.setRequestProperty("Content-type", "application/json; charset=UTF-8");
        
            OutputStream os = conn.getOutputStream();
            os.write(message.getBytes("UTF-8"));
            os.close();
        
            Scanner scan = new Scanner(conn.getInputStream());
            String jsonStr = "";
            while (scan.hasNext()) {
                jsonStr += scan.nextLine();
            }
            
            scan.close();
            return jsonStr;
        } catch (IOException ex) {
            System.out.println("EXCEPTION: "+ex.getMessage());
            throw new NotFoundException("Unable to connect");
        }
        
    }

    public String getAll() {
        return fetch(urls);
    }

    public String getByDate(String start, String end) {
        String[] formattedURLS = new String[urls.length];
        
        for(int i = 0; i < urls.length; i++) {
            formattedURLS[i] = urls[i] +  "?start=" + start + "&end=" + end;
        }
        
        return fetch(formattedURLS);
    }
    
    public String getByLocationAndDate(String location, String start, String end) {
        String[] formattedURLS = new String[urls.length];
        
        for(int i = 0; i < urls.length; i++) {
            formattedURLS[i] = urls[i] +  "?location=" + location + "&start=" + start + "&end=" + end;
        }
        
        return fetch(formattedURLS);
    }
    
    public String getByRegNo(String regNo) {
        String URL = "";
        if(regNo.startsWith("B"))
            URL = urls[0] + "/" + regNo;
        if(regNo.startsWith("L"))
            URL = urls[1] + "/" + regNo;
//        if(regNo.startsWith("D"))
//            URL = urls[2] + "/" + regNo;
        return fetchSpecificCar(URL);
    }
    
    public String putCar(String message, String regNo) {
        String URL = "";
        if(regNo.startsWith("B"))
            URL = urls[0] + "/" + regNo;
        if(regNo.startsWith("L"))
            URL = urls[1] + "/" + regNo;
//        if(regNo.startsWith("D"))
//            URL = urls[2] + "/" + regNo;
        
        System.out.println("URL IS: "+URL);
        
        return put(URL, message);
        //return put(baseURL + "/" + regno, message);
    }
    
}

