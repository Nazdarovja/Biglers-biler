/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.api;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alexander W. Hørsted-Andersen <awha86@gmail.com>
 */
public class CarResourceTest {
    
    public CarResourceTest() {
    }

    /**
     * Test of getJson method, of class CarResource.
     */
    @Test
    public void testGetJson() {
        System.out.println("getJson");
        CarResource instance = new CarResource();
        String expResult = "";
        String result = instance.getJson();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getThroughRegNo method, of class CarResource.
     */
    @Test
    public void testGetThroughRegNo() {
        System.out.println("getThroughRegNo");
        String regno = "";
        CarResource instance = new CarResource();
        String expResult = "";
        String result = instance.getThroughRegNo(regno);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getByDates method, of class CarResource.
     */
    @Test
    public void testGetByDates() {
        System.out.println("getByDates");
        String start = "";
        String end = "";
        CarResource instance = new CarResource();
        String expResult = "";
        String result = instance.getByDates(start, end);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getByLocationAndDates method, of class CarResource.
     */
    @Test
    public void testGetByLocationAndDates() {
        System.out.println("getByLocationAndDates");
        String location = "";
        String start = "";
        String end = "";
        CarResource instance = new CarResource();
        String expResult = "";
        String result = instance.getByLocationAndDates(location, start, end);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of putJson method, of class CarResource.
     */
    @Test
    public void testPutJson() {
        System.out.println("putJson");
        String regno = "";
        String message = "";
        CarResource instance = new CarResource();
        String expResult = "";
        String result = instance.putJson(regno, message);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
