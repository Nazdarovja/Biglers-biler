/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.api;

import backend.entities.CarList;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Stanislav
 */
public class CarRemoteFetchFacadeTest {
    
    Gson gson = new Gson();
    public CarRemoteFetchFacadeTest() {
    }
    
    @Before
    public void setUp() {
    }

    @Test
    public void testGetAll() {
        System.out.println("getAll");
        CarRemoteFetchFacade instance = new CarRemoteFetchFacade();
        String result = instance.getAll();
        assertTrue(result.length() > 1);
        
    }

    @Test
    public void testGetByRegNo() {
        System.out.println("getByRegNo");
        CarRemoteFetchFacade instance = new CarRemoteFetchFacade();
        String result = instance.getByRegNo("BAQ%201134");
        assertTrue(result.length() > 1);

    }
    
}
