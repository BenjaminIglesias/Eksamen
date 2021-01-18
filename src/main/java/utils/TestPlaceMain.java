/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import errorhandling.ErrorRetrieving;
import facades.FetchFacade;
import facades.PersonFacade;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import rest.PersonRessource;
import rest.UserRessource;
import security.errorhandling.AuthenticationException;

/**
 *
 * @author Benjamin
 */
public class TestPlaceMain {
    public static void main(String[] args) throws ErrorRetrieving, IOException, AuthenticationException {
         EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    EntityManager em = emf.createEntityManager();
      PersonFacade pf =  PersonFacade.getPersonFacade(emf);
 
        UserRessource pr = new  UserRessource(); 
        
        ;
          String payload = "{\n" +
        "  \"username\": \"firstName\",\n" +
        "  \"userpass\": \"1234\",\n" +
        "}";
   
        System.out.println(pr.addPerson(payload));
    }}
   
     
  