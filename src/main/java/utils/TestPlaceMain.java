/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import errorhandling.ErrorRetrieving;
import facades.FetchFacade;
import facades.PersonFacade;
import facades.UserFacade;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.json.JSONException;
import rest.PersonRessource;
import rest.UserRessource;
import security.errorhandling.AuthenticationException;

/**
 *
 * @author Benjamin
 */
public class TestPlaceMain {
    public static void main(String[] args) throws ErrorRetrieving, IOException, AuthenticationException, JSONException {
         EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    EntityManager em = emf.createEntityManager();
      PersonFacade pf =  PersonFacade.getPersonFacade(emf);
      UserFacade uf = UserFacade.getUserFacade(emf);
      UserRessource pr = new  UserRessource(); 
        
        ;
          String payload = "{\n" +
        "  \"username\": \"firstName\",\n" +
        "  \"userpass\": \"1234\",\n" +
        "}";
      FetchFacade ff = FetchFacade.getFetchFacade(emf);
        System.out.println( ff.getWeatherInfo("Roskilde").toString());
     
    }}
   
     
  