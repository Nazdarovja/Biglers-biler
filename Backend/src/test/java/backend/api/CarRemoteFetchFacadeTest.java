/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.api;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Stanislav, Alexander
 */
public class CarRemoteFetchFacadeTest {

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
        assertTrue(result.length() > 10);

    }

    @Test
    public void testGetByRegNo() {
        System.out.println("getByRegNo");
        CarRemoteFetchFacade instance = new CarRemoteFetchFacade();
        String result = instance.getByRegNo("BAK1134");
        assertTrue(result.length() > 10);

    }

    /**
     * Test of getByDate method, of class CarRemoteFetchFacade.
     */
    @Test
    public void testGetByDate() {
        System.out.println("getByDate");
        String start = "01/01/2025";
        String end = "08/01/2025";
        CarRemoteFetchFacade instance = new CarRemoteFetchFacade();
        String result = instance.getByDate(start, end);
        assertTrue(result.length() > 10);
    }

    /**
     * Test of getByLocationAndDate method, of class CarRemoteFetchFacade. *
     * !!!!!!!! METHOD NOT YET IMPLEMENTED - IF THIS FAILS, METHOD IS
     * IMPLEMENTED; REWRITE THIS TEST !!!!!!!!!!
     *
     */
    @Test
    public void testGetByLocationAndDate() {

        System.out.println("getByLocationAndDate");
        String location = "Cph (Copenhagen Airport)";
        String start = "01/01/2025";
        String end = "08/01/2025";
        CarRemoteFetchFacade instance = new CarRemoteFetchFacade();
        String result = instance.getByLocationAndDate(location, start, end);
        assertNotNull(result);
        assertTrue(result.length() > 1);
    }

    /**
     * Test of putCar method, of class CarRemoteFetchFacade.
     * This test method generates a random reservation date in the format dd/mm/yyyy , where year is 4xxx, and the customerMail is JUNIT@JUNIT.DK (for easier cleanup)
     * This date is used for both from & to dates (to lessen chance of collusion).
     * this new reservation is added to the car with regNo "BAG1234".
     * Before the reservation is added, the car is fetched, and again after. Then 
     */
    @Test
    public void testPutCar() {

        System.out.println("putCar");

        //generate random new reservation time - pray for no collusion 
        int randomDay = ThreadLocalRandom.current().nextInt(1, 27 + 1);  
        int randomMonth = ThreadLocalRandom.current().nextInt(1, 11 + 1);
        int randomYear = ThreadLocalRandom.current().nextInt(4000, 4998 + 1);

        String newDate = randomDay + "/" + randomMonth + "/" + randomYear;
        System.out.println("newdate"+newDate);
        //original car
        CarRemoteFetchFacade instance = new CarRemoteFetchFacade();
        String original_car = instance.getByRegNo("BAG1234");

        //updated car, reservation
        String regno = "BAG1234";
        String message = "{  \n"
                + "   \"cars\":[  \n"
                + "      {  \n"
                + "         \"logo\":\"https://i.imgur.com/t0qqq7l.png\",\n"
                + "         \"company\":\"Biglers Biler\",\n"
                + "         \"category\":\"Mini\",\n"
                + "         \"picture\":\"https://images.toyota-europe.com/dk/ag/width/1200/exterior-3.jpg\",\n"
                + "         \"make\":\"Toyota\",\n"
                + "         \"model\":\"Aygo\",\n"
                + "         \"year\":2017,\n"
                + "         \"regno\":\"BAG1234\",\n"
                + "         \"seats\":4,\n"
                + "         \"doors\":4,\n"
                + "         \"gear\":\"manual\",\n"
                + "         \"aircondition\":false,\n"
                + "         \"location\":\"Billund Lufthavn\",\n"
                + "         \"priceperday\":30,\n"
                + "         \"reservations\":[  \n"
                + "            {  \n"
                + "               \"companyTag\":\"Biglers biler\",\n"
                + "               \"customerMail\":\"JUNIT@JUNIT.DK\",\n"
                + "               \"fromDate\":\"" + newDate + "\",\n"
                + "               \"toDate\":\"" + newDate + "\"\n"
                + "            }\n"
                + "         ]\n"
                + "      }]}";

        //put new reservation
        instance.putCar(message, regno);

        //get car again
        String result = instance.getByRegNo("BAG1234");
        
        //assert that car has new reservation data added
        assertTrue(result.length() > original_car.length());
    }

}
