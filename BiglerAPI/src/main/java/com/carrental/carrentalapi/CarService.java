package com.carrental.carrentalapi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.carrental.helper.ErrorMessageUtility;
import com.carrental.helper.Util;
import com.carrental.model.Car;
import com.carrental.model.Cars;
import com.carrental.model.ErrorObject;
import com.carrental.model.Reservation;

/**
 * Root resource (exposed at "cars" path) Requirements
 *
 * All get methods should return an array of cars even though it's only 1 car.
 * GET ..api/cars (returns all cars also rented) ../api/cars/xxxx (returns a
 * specific cars with regno=xxxx) ../api/cars?location=xxxx (returns only cars
 * from specific location) ../api/cars?category=xxxx (returns only cars vacant
 * within that category) ../api/cars?start=xxxx&end=xxxx (returns only cars
 * vacant in this period) ../api/cars?location=xxxx&start=xxxx&end=xxxx (returns
 * only cars vacant from specific location)
 * ../api/cars?category=xxxx&start=xxxx&end=xxxx (returns only cars vacant
 * within that category)
 * ../api/cars?location=xxxx&category=xxxx&start=xxxx&end=xxxx (returns only
 * cars vacant within that category on a specific location) PUT:
 * ../api/cars/xxxx + car json object (hvor xxxx = regno) (changes a specific
 * car in the json object to be rented in this period)
 *
 */
@Path("cars")
public class CarService {

    final static Logger logger = Logger.getLogger(CarService.class.getName());
    private static final String PERSISTENCE_UNIT_NAME = "cars";
    private static EntityManagerFactory factory;

    /**
     * ..api/cars
     *
     * @return String returns all cars also rented
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCars(@QueryParam("location") String location, @QueryParam("category") String category,
            @QueryParam("start") String queryStartDate, @QueryParam("end") String queryEndDate) {
        String jpql = "";
        logger.info("Inside getCars");
        logger.info("location: " + location);
        logger.info("category: " + category);

        /* Validate the input here */
        Response isValid = validateInput(location, category, queryStartDate, queryEndDate);
        if (null != isValid) {
            return isValid;
        }

        /* Requirement of API #1 ../api/cars says returns all cars also rented. 
		 * To meet this I have added this if statement. if location, category, start, end are present then we need
		 * to return ONLY vacant Cars. If NONE of these paramters are present we need to return 
		 * the RENTED Cars too. So in that scenario reservation check is not required.
         */
        if (Util.isNullOrBlank(queryStartDate) && Util.isNullOrBlank(queryEndDate)
                && Util.isNullOrBlank(location) && Util.isNullOrBlank(category)) {
            jpql = "SELECT c FROM Car as c";
        } else {
            jpql = "SELECT c FROM Car as c "
                    + " WHERE NOT EXISTS (SELECT r from c.reservations r "
                    + "	WHERE ((r.fromDate >= :queryStartDate AND r.fromDate <= :queryEndDate)"
                    + " OR (r.toDate >= :queryStartDate AND r.toDate <= :queryEndDate)"
                    + " OR (r.fromDate <= :queryStartDate AND r.toDate >= :queryEndDate)"
                    + " OR (r.fromDate >= :queryStartDate AND r.toDate <= :queryEndDate))) ";
        }

        if (!Util.isNullOrBlank(category)) {  // Add the condition for Category
            jpql += " AND c.category = '" + category + "'";
        }

        if (!Util.isNullOrBlank(location)) {  // Add the condition for loation
            jpql += " AND c.location = '" + location + "'";
        }

        /* If dates are NULL set to current date, since we need to find current vacant cars
		 * if the start/end is null. 
         */
        if (Util.isNullOrBlank(queryStartDate)) {
            queryStartDate = getCurrentDate();
        }
        if (Util.isNullOrBlank(queryEndDate)) {
            queryEndDate = getCurrentDate();
        }

        Date startDate = null;
        Date endDate = null;

        try {
            startDate = Util.converStringtoDate(queryStartDate);
            endDate = Util.converStringtoDate(queryEndDate);
        } catch (ParseException e) {
            // This exception should not occur logically as input has been validated above.
        }

        // Query related Stuff
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Query q = em.createQuery(jpql);
        try {
            q.setParameter("queryStartDate", startDate, TemporalType.DATE);
            q.setParameter("queryEndDate", endDate, TemporalType.DATE);
        } catch (IllegalArgumentException ae) {
        }

        // Prepare Response
        List<Car> car = q.getResultList();
        Cars cars = new Cars();
        cars.setCars(car);
        logger.info("Car:" + cars);
        return Response.status(Response.Status.OK).entity(cars).build();

    }

    /**
     * URL: /api/cars/xxxx This API Is used to create the CARs along with the
     * reservations. If the CAR is already there and ONLY the reservations are
     * added.
     *
     * @return
     */
    @PUT
    @Path("{regno}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCarWithRegNo(Cars cars) {
        return createCar(cars);
    }

    /**
     * URL: /api/cars This API Is used to create the CARs along with the
     * reservations. If the CAR is already there and ONLY the reservations are
     * added.
     *
     * @return
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCar(Cars cars) {
        logger.info("Inside createCar");
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        ArrayList<Car> carList = (ArrayList<Car>) cars.getCars();
        logger.info("carList size: " + carList.size());

        for (Car car : carList) {
            logger.info("inside carList");
            ArrayList<Reservation> ReservationList = (ArrayList<Reservation>) car.getReservations();
            logger.info("ReservationList Size: " + ReservationList.size());

            String regNo = car.getRegno();
            Car carDB = isCarAlreadyExist(em, regNo);  // Only persist if Car is Not already present in the system

            if (null != carDB) {
                car = carDB;
            } else {
                em.persist(car);
            }

            for (Reservation reservation : ReservationList) {
                logger.info("inside reservation");
                Date fromDate = reservation.getFromDate();
                Date toDate = reservation.getToDate();

                Response isReservationValid = validateReservation(reservation);
                if (isReservationValid != null) return isReservationValid;
                

                if (!isReservationAlreadyExisting(em, car, reservation)) {
                    car.addReservation(reservation);
                    em.merge(car);
                } else {
                    ErrorObject result = ErrorMessageUtility.getErrorJson(451, "Reservation Already Exists "+ "CAR REGNO : " + car.getRegno(), reservation.toString());
                    return Response.status(Response.Status.BAD_REQUEST).entity(result).type("application/json").build();
                }
            }
        }

        em.getTransaction().commit();
        return Response.status(Response.Status.OK).build();
    }

    /**
     * Validate Reservation
     *
     * This method helps in validating the reservation. We would proceed ahead
     * with reservation only if it is valid.
     *
     * @param reservation
     * @return
     */
    private Response validateReservation(Reservation reservation) {
        String companyTag = reservation.getCompanyTag();
        String customerMail = reservation.getCustomerMail();
        Date fromDate = reservation.getFromDate();
        Date toDate = reservation.getToDate();

        if (Util.isNullOrBlank(companyTag)) {
            ErrorObject result = ErrorMessageUtility.getErrorJson(461, "Company Tag Is Blank", reservation.toString());
            return Response.status(Response.Status.BAD_REQUEST).entity(result).type("application/json").build();
        }

        if (Util.isNullOrBlank(customerMail)) {
            ErrorObject result = ErrorMessageUtility.getErrorJson(462, "Customer Email Is Blank", reservation.toString());
            return Response.status(Response.Status.BAD_REQUEST).entity(result).type("application/json").build();
        }

        if (null == fromDate) {
            ErrorObject result = ErrorMessageUtility.getErrorJson(463, "fromDate can not be null", reservation.toString());
            return Response.status(Response.Status.BAD_REQUEST).entity(result).type("application/json").build();
        }

        if (null == toDate) {
            ErrorObject result = ErrorMessageUtility.getErrorJson(464, "toDate can not be null", reservation.toString());
            return Response.status(Response.Status.BAD_REQUEST).entity(result).type("application/json").build();
        }

        if (fromDate.after(toDate)) {
            ErrorObject result = ErrorMessageUtility.getErrorJson(465, "fromDate can not be after toDate", reservation.toString());
            return Response.status(Response.Status.BAD_REQUEST).entity(result).type("application/json").build();
        }

        return null;
    }

    private Response validateInput(String location, String category, String queryStartDate, String queryEndDate) {

        try {
            if (!Util.isNullOrBlank(queryStartDate)) {
                Date startDate = Util.converStringtoDate(queryStartDate);
            }
        } catch (ParseException e) {
            ErrorObject result = ErrorMessageUtility.getErrorJson(471, "start date format is not correct", queryStartDate);
            return Response.status(Response.Status.BAD_REQUEST).entity(result).type("application/json").build();
        }

        try {
            if (!Util.isNullOrBlank(queryStartDate)) {
                Date endDate = Util.converStringtoDate(queryEndDate);
            }
        } catch (ParseException e) {
            ErrorObject result = ErrorMessageUtility.getErrorJson(472, "end date format is not correct", queryStartDate);
            return Response.status(Response.Status.BAD_REQUEST).entity(result).type("application/json").build();
        }

        return null;

    }

    /**
     * API: ../api/cars/xxxx (returns a specific cars with regno=xxxx)
     *
     * This method is used to get the CAR with the given regno This would also
     * fetch all the reservations corresponding to that car.
     *
     *
     * @param regno
     * @return cars object. Blank object if the are with the given regno is not
     * found.
     */
    @GET
    @Path("{regno}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCarWithRegNo(@PathParam("regno") String regno) {
        logger.info("inside getCar");
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Query q = em.createQuery("select c from Car c where c.regno = '" + regno + "'");
        List<Car> car = q.getResultList();
        Cars cars = new Cars();
        cars.setCars(car);
        logger.info("Car:" + car);
        // return Response.status(Response.Status.OK).build();
        return Response.status(Response.Status.OK).entity(cars).build();
    }

    /**
     * /api/cars?location=xxxx
     *
     * @return only cars from specific location
     */
    /*
	 * @GET
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public String
	 * getCarByLocation(@QueryParam("location") String location) {
	 * logger.info("inside getCarByLocation"); factory =
	 * Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME); EntityManager
	 * em = factory.createEntityManager(); // read the existing entries and write to
	 * console Query q = em.
	 * createQuery("select * from car as c left join reservation as r where c.id = r.car_id and c.location = '"
	 * + location + "'"); List<Car> todoList = q.getResultList();
	 * logger.info("Size: " + todoList.size()); return "Got it!"; }
	 * 
     */
    private String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String formattedDate = sdf.format(date);
        return formattedDate;
    }

    /**
     * Is Car already existing ? Since are are using a PUT object of car to PUT
     * new reservation, it is important to check.
     *
     * If CAR doesn't exists THEN we add the car.
     *
     * IF the CAR exists THEN we ONLY add the reservations.
     *
     * @param em
     * @param regno
     *
     * @return
     */
    private Car isCarAlreadyExist(EntityManager em, String regno) {
        logger.info("Inside isCarAlreadyExist");
        Query q = em.createQuery("select c from Car c where c.regno = '" + regno + "'");
        List<Car> car = q.getResultList();
        if (car.size() > 0) {
            logger.info("Car Exists");
            return car.get(0);
        }
        logger.info("Car Doesn Not Exists");
        return null;
    }

    /**
     * This method checks if the Reservation is already existing. It Verified
     * for the overlapping dates for the regno with fromDate and toDate.
     *
     * @param em
     * @param car
     * @param newStartDate
     * @param newEndDate
     *
     * @return true if the overlapping reservation exists, false otherwise.
     */
    private boolean isReservationAlreadyExisting(EntityManager em, Car car, Reservation newReservation) {
        logger.info("Inside isReservationAlreadyExisting");
        Date newStartDate = newReservation.getFromDate();
        Date newEndDate = newReservation.getToDate();
        String regno = car.getRegno();
        Query q
                = em.createQuery("SELECT c FROM Car c INNER JOIN c.reservations r where c.regno = :regno "
                        + " AND ((r.fromDate >= :queryStartDate AND r.fromDate <= :queryEndDate)"
                        + " OR (r.toDate >= :queryStartDate AND r.toDate <= :queryEndDate)"
                        + " OR (r.fromDate <= :queryStartDate AND r.toDate >= :queryEndDate)"
                        + " OR (r.fromDate >= :queryStartDate AND r.toDate <= :queryEndDate))");

        q.setParameter("regno", regno);
        q.setParameter("queryStartDate", newStartDate, TemporalType.DATE);
        q.setParameter("queryEndDate", newEndDate, TemporalType.DATE);

        List<Car> resultSet = q.getResultList();

        if (resultSet.size() > 0) {
            logger.info("Reservation Does Exists: " + regno + ", newStartDate: " + newStartDate + ", newEndDate: " + newEndDate);
            List<Reservation> r = (List<Reservation>) resultSet.get(0).getReservations();
            for (int i = 0; i < r.size(); i++) {
                logger.info("Reservation:" + r.get(i));
            }
            return true;
        }

        logger.info("Reservation Does Not Exists: " + regno);
        return false;
    }

    private String addCondition(String condition, String fragment) {
        if ("".equals(condition)) {
            condition = " WHERE ";
        } else {
            condition += " AND ";
        }

        condition += fragment;

        return condition;
    }

}
