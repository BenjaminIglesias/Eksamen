/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entities.Activity;
import entities.CityInfo;
import entities.User;
import errorhandling.API_Exception;
import errorhandling.ErrorRetrieving;
import facades.ActivityFacade;
import facades.FetchFacade;

import facades.UserFacade;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import org.json.JSONException;
import rest.ActivityRessource;
import rest.UserRessource;
import security.errorhandling.AuthenticationException;

/**
 *
 * @author Benjamin
 */
public class TestPlaceMain {
    public static void main(String[] args) throws ErrorRetrieving, IOException, AuthenticationException, JSONException, API_Exception {
         EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    EntityManager em = emf.createEntityManager();
      UserFacade uf = UserFacade.getUserFacade(emf);
      UserRessource pr = new  UserRessource(); 
      
        ;
          String payload = "{\n" +
        "  \"username\": \"firstName\",\n" +
        "  \"userpass\": \"1234\",\n" +
        "}";
      FetchFacade ff = FetchFacade.getFetchFacade(emf);
    
    uf.getUsers();

    }}
   
     
  