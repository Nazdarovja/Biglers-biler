package com.carrental.helper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.carrental.interfaces.IConstants;
 
@Provider
public class CustomDateDeserializer extends JsonDeserializer<Date> implements IConstants {

    private SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
  
    @Override
    public Date deserialize(JsonParser jsonparser, DeserializationContext context)
      throws IOException, JsonProcessingException {

    	String date = jsonparser.getText();
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}