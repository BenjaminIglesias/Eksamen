/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Activity;
import entities.User;
import errorhandling.ErrorRetrieving;
import facades.ActivityFacade;

import facades.UserFacade;
import java.io.IOException;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.JSONException;
import security.errorhandling.AuthenticationException;
import utils.EMF_Creator;

/**
 *
 * @author Benjamin
 */
@Path("User")
public class UserRessource {
        private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
   private static final UserFacade userFacade =  UserFacade.getUserFacade(EMF);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("user")
    @Path("getAll")
    public String getAllPersons() throws ErrorRetrieving {
      
      return GSON.toJson( userFacade.getUsers());    
      
      
    }

    @Path("add")
    @POST
   
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String addPerson(String user) throws AuthenticationException {
       
        User u = GSON.fromJson(user, User.class);
        userFacade.addNewUser(u);
        
        return  "{\"msg\": \"Bruger oprettet: " + u.getUserName() + "\"}";
}

   @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("fillDB")
    public String fillDB() throws ErrorRetrieving, JSONException, IOException {
      
      return GSON.toJson( userFacade.fillDB());    
      
      
    }

   
}
