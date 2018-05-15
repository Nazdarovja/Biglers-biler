/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Stanislav
 */
@Path("car")
public class CarResource {

    @Context
    private UriInfo context;

    //TODO: TEST STUFF REMOVE LATER//
    
    CarRemoteFetchFacade carRemote = new CarRemoteFetchFacade();

    /**
     * Creates a new instance of CarResource
     */
    public CarResource() {
    }

    /**
     * Retrieves representation of an instance of backend.api.CarResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
         return carRemote.getAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/regno/{regno}")
    public String getThroughRegNo(@PathParam("regno")String regno) {
        return carRemote.getByRegNo(regno.toUpperCase());
    }
    
    @GET
    @Path("/date")
    @Produces(MediaType.APPLICATION_JSON)
    public String getByDates(@QueryParam("start") String start, @QueryParam("end") String end) {
        return carRemote.getByDate(start, end);
    }
    
    @GET
    @Path("/location")
    @Produces(MediaType.APPLICATION_JSON)
    public String getByLocationAndDates(@QueryParam("location") String location, @QueryParam("start") String start, @QueryParam("end") String end) {
        return carRemote.getByLocationAndDate(location, start, end);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/regno/{regno}")
    public String putJson(@PathParam("regno")String regno, String message) {
        return carRemote.putCar(message, regno);
    }
}
