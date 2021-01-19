package facades;

import dto.UserDTO;
import dto.UsersDTO;
import entities.Activity;
import entities.CityInfo;
import entities.Role;

import entities.User;
import entities.UserInfo;
import errorhandling.ErrorRetrieving;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import org.json.JSONException;
import security.errorhandling.AuthenticationException;
import utils.EMF_Creator;

/**
 * @author lam@cphbusiness.dk
 */
public class UserFacade {

    private static EntityManagerFactory emf;
    private static UserFacade instance;

    public UserFacade() {
    }

    /**
     *
     * @param _emf
     * @return the instance of this facade.
     */
    public static UserFacade getUserFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new UserFacade();
        }
        return instance;
    }

    public User getVeryfiedUser(String username, String password) throws AuthenticationException {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = em.find(User.class, username);
            if (user == null || !user.verifyPassword(password)) {
                throw new AuthenticationException("Invalid user name or password");
            }
        } finally {
            em.close();
        }
        return user;
    }
 
          public User addNewUser(User newUser) throws AuthenticationException{
        
        
         if (newUser.getUserName().isEmpty() || newUser.getUserPass().isEmpty() ) {
                throw new AuthenticationException("User name or password must not be empty");   
            }
        
          EntityManager em = emf.createEntityManager();
        
          User user = new User(newUser.getUserName(), newUser.getUserPass());
          user.setUserInfo(newUser.getUserInfo());
          user.addRole(new Role("user"));
          
        try {
          em.getTransaction().begin();
          em.persist(user);
          em.getTransaction().commit();
           
        } catch (RollbackException e) {
            throw new AuthenticationException("User already exist. Try another username"+ e.getMessage());
            
        } catch (Exception e){
            System.out.println(e);              
        } finally {
            em.close();
        }
        return user;

        
    }

    public List<UserDTO> getUsers(){
         EntityManager em = emf.createEntityManager();
          try {
        List<User> Users = em.createQuery("Select u from User u" , User.class).getResultList();
        return new UsersDTO(Users).getAll();
          } 
          finally{
          em.close();
          }
    
    }

    public String fillDB() throws JSONException, IOException{
         EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    EntityManager em = emf.createEntityManager();
    

    
    // IMPORTAAAAAAAAAANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // This breaks one of the MOST fundamental security rules in that it ships with default users and passwords
    // CHANGE the three passwords below, before you uncomment and execute the code below
    // Also, either delete this file, when users are created or rename and add to .gitignore
    // Whatever you do DO NOT COMMIT and PUSH with the real passwords

    //User user = new User("user", "test1");
      FetchFacade uf = FetchFacade.getFetchFacade(emf);
     
    User user = new User("bobby", "bobby");
    User admin = new User("admin", "test1");
    User both = new User("user_admin", "test1");
    user.setUserInfo(new UserInfo("Erik Larsen",33,75.5));
    admin.setUserInfo(new UserInfo("Lars Larsen",33,75.5));
    both.setUserInfo(new UserInfo("Henrik Larsen",33,75.5));
    user.addActivity(new Activity("Cross",30.0,13.0,"god tur"));
    user.getActivities().get(0).setWeatherInfo(uf.getWeatherInfo("Roskilde"));
    CityInfo ci = new CityInfo(uf.getCityInfo("Roskilde"));
    user.getActivities().get(0).setCityInfo(ci);
 
    
    if(admin.getUserPass().equals("test")||user.getUserPass().equals("test")||both.getUserPass().equals("test"))
      throw new UnsupportedOperationException("You have not changed the passwords");

    em.getTransaction().begin();
    Role userRole = new Role("user");
    Role adminRole = new Role("admin");
    user.addRole(userRole);
    admin.addRole(adminRole);
    both.addRole(userRole);
    both.addRole(adminRole);
    em.persist(ci);
    em.persist(userRole);
    em.persist(adminRole);
    em.persist(user);
    em.persist(admin);
    em.persist(both);
    em.getTransaction().commit();
    System.out.println("PW: " + user.getUserPass());
    System.out.println("Testing user with OK password: " + user.verifyPassword("test"));
    System.out.println("Testing user with wrong password: " + user.verifyPassword("test1"));
    System.out.println("Created TEST Users");
   
      
      
        
        
        
        
    return "db filled";
    }

}


 