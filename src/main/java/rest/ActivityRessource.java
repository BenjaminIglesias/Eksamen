/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.ActivityDTO;
import entities.Activity;
import entities.User;
import errorhandling.API_Exception;
import errorhandling.ErrorRetrieving;
import facades.ActivityFacade;

import java.io.IOException;
import javax.annotation.security.RolesAllowed;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.JSONException;
import security.errorhandling.AuthenticationException;
import utils.EMF_Creator;

/**
 *
 * @author Benjamin
 */
@Path("Activity")
public class ActivityRessource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
 
    private static final ActivityFacade activityFacade=  ActivityFacade.getActivityFacade(EMF);
 
    
       
       @POST
    @Path("add/{name}/{userName}")
       @RolesAllowed("user")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String addActivity(@PathParam("name") String name, @PathParam("userName") String userName , String activity) throws AuthenticationException, JSONException, IOException {
         
        Activity a = GSON.fromJson(activity, Activity.class);
     
        activityFacade.addActivity(userName, name , a);
        
        return  "Nice job, u did "  + a.getDistance() +" Km" + ", also ur activity has been created";
}
    
      @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("user")
      @Path("getAll/{userName}")
    public String getAllPersons(@PathParam("userName") String userName) throws ErrorRetrieving {
      
      return GSON.toJson( activityFacade.getActivities(userName));    
      
      
    }
    
      @PUT
    @Path("update/{id}")
      @RolesAllowed("user")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String updatePerson(@PathParam("id") Long id, String activity) throws API_Exception{
     return activityFacade.editActivity(id,GSON.fromJson(activity, Activity.class));
}
    
    
}
