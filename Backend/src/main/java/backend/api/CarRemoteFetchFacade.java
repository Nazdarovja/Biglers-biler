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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stanislav
 */
public class CarRemoteFetchFacade {
    
    Gson gson = new Gson();
    List<String> urls;
    String baseURL = "https://stanitech.dk/carrentalapi/api/cars";

    public CarRemoteFetchFacade() {
        urls = new ArrayList<>();
        urls.add("https://stanitech.dk/carrentalapi/api/cars");
        urls.add("http://www.ramsbone.dk:8081/api/cars");
    }

    private String fetch(String url) {

        URL address;
        try {
            address = new URL(url);
        } catch (MalformedURLException ex) {
            throw new NotFoundException("URL not found");
        }
        HttpURLConnection conn;
        try {
            conn = (HttpURLConnection) address.openConnection();
        } catch (IOException ex) {
            throw new NotFoundException("Unable to connect");
        }

        try {
            conn.setRequestMethod("GET");
        } catch (ProtocolException ex) {
            throw new NotFoundException("Internal error happened!");
        }
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("User-Agent", "server");

        Scanner scan;
        try {
            scan = new Scanner(conn.getInputStream());
        } catch (IOException ex) {
            throw new NotFoundException("Unable to connect");
        }
        String jsonStr = "";
        while (scan.hasNext()) {
            jsonStr += scan.nextLine();
        }
        scan.close();
        return jsonStr;
    }
    
    private String put(String url, String message) {

        URL address;
        try {
            address = new URL(url);
        } catch (MalformedURLException ex) {
            throw new NotFoundException("URL not found");
        }
        HttpURLConnection conn;
        try {
            conn = (HttpURLConnection) address.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
        } catch (IOException ex) {
            throw new NotFoundException("Unable to connect");
        }

        try {
            conn.setRequestMethod("PUT");
        } catch (ProtocolException ex) {
            throw new NotFoundException("Internal error happened!");
        }
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("User-Agent", "server");
        conn.setRequestProperty("Content-type", "application/json; charset=UTF-8");
        
        OutputStream os;
        try {
            os = conn.getOutputStream();
            os.write(message.toString().getBytes("UTF-8"));
            os.close();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(CarRemoteFetchFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CarRemoteFetchFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Scanner scan;
        try {
            scan = new Scanner(conn.getInputStream());
        } catch (IOException ex) {
            throw new NotFoundException("Unable to connect");
        }
        String jsonStr = "";
        while (scan.hasNext()) {
            jsonStr += scan.nextLine();
        }
        scan.close();
        return jsonStr;
        
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
