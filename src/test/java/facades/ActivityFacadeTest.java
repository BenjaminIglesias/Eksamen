/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Activity;
import entities.Role;
import entities.User;
import errorhandling.API_Exception;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.json.JSONException;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import security.errorhandling.AuthenticationException;
import utils.EMF_Creator;

/**
 *
 * @author Benjamin
 */
public class ActivityFacadeTest {
         private static EntityManagerFactory emf = EMF_Creator.createEntityManagerFactoryForTest();

    private static ActivityFacade activityFacade;
    
    public ActivityFacadeTest() {
    }
    
    @BeforeEach
    public void setUpClass() {
        
         activityFacade = activityFacade.getActivityFacade(emf);
         EntityManager em = emf.createEntityManager();
         
        try {
              em.getTransaction().begin();
            //Delete existing users and roles to get a "fresh" database
            em.createQuery("delete from User").executeUpdate();
            em.createQuery("delete from Role").executeUpdate();

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
@Disabled
    @Test
    public void addActivity() throws AuthenticationException, JSONException, IOException{
       EntityManager em = emf.createEntityManager();
      User test = new User("test","test");
      em.getTransaction().begin();
      em.persist(test);
      em.getTransaction().commit();
      
      
    activityFacade.addActivity(test.getUserName(), "Herlev", new Activity("test",0,0,"test"));
    
    em.getTransaction().begin();
    User testReturned = em.find(User.class, "test");
    em.close();
    assertEquals(testReturned.getActivities().get(0).getExerciseType() ,"test");
    
    }
    @Disabled
    @Test
    public void editActivity() throws AuthenticationException, JSONException, IOException, API_Exception{
       EntityManager em = emf.createEntityManager();
      User test = new User("test","test");
      test.addActivity(new Activity("test",30,30,"test"));
      em.getTransaction().begin();
      em.persist(test);
      em.getTransaction().commit();
      
    long l = 1;  
    activityFacade.editActivity(l , new Activity("testÆndret",0,0,"testÆndret"));
    em.getTransaction().begin();
    Activity testReturned = em.find(Activity.class, l );
    em.close();
    assertEquals("testÆndret", testReturned.getExerciseType());
    
    }
    
}
