/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.ActivitiesDTO;
import dto.ActivityDTO;
import dto.CombinedActivityDTO;
import entities.Activity;
import entities.CityInfo;
import entities.User;
import errorhandling.API_Exception;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import org.json.JSONException;
import security.errorhandling.AuthenticationException;

/**
 *
 * @author Benjamin
 */
public class ActivityFacade {
       private static EntityManagerFactory emf;
      private static ActivityFacade instance;
      private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    
   
    public ActivityFacade(){}
      /**
     *
     * @param _emf
     * @return the instance of this facade.
     */
    public static ActivityFacade getActivityFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ActivityFacade();
        }
        return instance;
    }
    
    
    
    public String addActivity(String userName, String cityName, Activity activity) throws AuthenticationException, JSONException, IOException{
       
          EntityManager em = emf.createEntityManager();
          FetchFacade ff = FetchFacade.getFetchFacade(emf);
          //check if cityInfo exists
         CityInfo returned = null;
          
          
          
          
          //send to db
          
          
          
        try {
          em.getTransaction().begin();
         CityInfo city =  new CityInfo(ff.getCityInfo(cityName));
          em.persist(city);
          User user = em.find(User.class, userName);
          user.addActivity(activity);
          
          user.getActivities().get(user.getActivities().size() - 1).setCityInfo(city);
          user.getActivities().get(user.getActivities().size() -1).setWeatherInfo(ff.getWeatherInfo(cityName));
          
          em.getTransaction().commit();
           
        } catch (RollbackException e) {
            throw new AuthenticationException("User already exist. Try another username"+ e.getMessage());
            
        } catch (Exception e){
              System.out.println(e);
        } finally {
            em.close();
        }
    return null;
    }

       public List<ActivityDTO> getActivities(String userName){
         EntityManager em = emf.createEntityManager();
          try {
           User user = em.find(User.class, userName);
          Query query = em.createQuery("Select a from Activity a where a.user = :name");
       query.setParameter("name", user);
        List<Activity> activities = query.getResultList();
       
        
        
        return new ActivitiesDTO(activities).getAll();
          } 
          finally{
          em.close();
          }
    
    }
     public String editActivity(long id, Activity activity) throws API_Exception{
       EntityManager em = emf.createEntityManager();
       
       try {
            em.getTransaction().begin();
            
           Activity a = em.find(Activity.class, id);
           a.setComment(activity.getComment());
           a.setDistance(activity.getDistance());
           a.setDuration(activity.getDuration());
           a.setExerciseType(activity.getExerciseType());
             em.getTransaction().commit();
       } catch (Exception e){
           
           throw new API_Exception(e.getMessage());
           
       } finally {
           em.close();
       }
       
       return "complete";
   }
     }

