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
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
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
    };
    String baseURL = "https://stanitech.dk/carrentalapi/api/cars";
    ExecutorService es = Executors.newFixedThreadPool(10);
    List<Future<String>> list = new ArrayList<Future<String>>();

    public CarRemoteFetchFacade() {
    }

    private String fetch(String url) {
        
        //TODO tråde til at hente fra alle URL'er
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
            os.write(message.toString().getBytes("UTF-8"));
            os.close();
        
            Scanner scan = new Scanner(conn.getInputStream());
            String jsonStr = "";
            while (scan.hasNext()) {
                jsonStr += scan.nextLine();
            }
            scan.close();
            return jsonStr;
        } catch (IOException ex) {
            throw new NotFoundException("Unable to connect");
        }
        
    }

    public String getAll() {
        String URL = baseURL;
        return fetch(URL);
    }

    public String getByDate(String start, String end) {
        String URL = baseURL + "?start=" + start + "&end=" + end;
        return fetch(URL);
    }
    
    public String getByLocationAndDate(String location, String start, String end) {
        String URL = baseURL + "?location=" + location + "&start=" + start + "&end=" + end;
        return fetch(URL);
    }
    
    public String getByRegNo(String regNo) {
        String URL = baseURL + "?regno=" + regNo;
        return fetch(URL);
    }
    
    public String putCar(String message, String regno) {
        return put(baseURL + "/" + regno, message);
    }
}
