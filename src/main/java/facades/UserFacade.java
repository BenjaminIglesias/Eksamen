package facades;

import dto.UserDTO;
import dto.UsersDTO;
import entities.Role;

import entities.User;
import errorhandling.ErrorRetrieving;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import security.errorhandling.AuthenticationException;

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


}


 