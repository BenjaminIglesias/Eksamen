/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.UserDTO;
import entities.Role;
import entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import security.errorhandling.AuthenticationException;
import utils.EMF_Creator;


/**
 *
 * @author Benjamin
 */
public class UserFacadeTest {
       private static EntityManagerFactory emf = EMF_Creator.createEntityManagerFactoryForTest();

    private static UserFacade userFacade;
    
    public UserFacadeTest() {
    }
    
    @BeforeEach
    public void setUpClass() {
        
        userFacade = UserFacade.getUserFacade(emf);
         EntityManager em = emf.createEntityManager();
       
        try {
              em.getTransaction().begin();
            //Delete existing users and roles to get a "fresh" database
            em.createQuery("delete from User").executeUpdate();
            em.createQuery("delete from Role").executeUpdate();
              em.createQuery("delete from Activity").executeUpdate();
            Role userRole = new Role("user");
            Role adminRole = new Role("admin");
            User user = new User("user", "test");
            user.addRole(userRole);
            User admin = new User("admin", "test");
            admin.addRole(adminRole);
            User both = new User("user_admin", "test");
            both.addRole(userRole);
            both.addRole(adminRole);
            em.persist(userRole);
            em.persist(adminRole);
            em.persist(user);
            em.persist(admin);
            em.persist(both);
            //System.out.println("Saved test data to database");
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

  @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    
  @Test
  public void addUserTest() throws AuthenticationException{
    EntityManager em = emf.createEntityManager();
    User testUser = new User("test","test");
    em.getTransaction().begin();
   userFacade.addNewUser(testUser);
    User u = em.find(User.class, "test");
   
    assertEquals(u.getUserName(),testUser.getUserName());
em.close();
  }
  
 
}