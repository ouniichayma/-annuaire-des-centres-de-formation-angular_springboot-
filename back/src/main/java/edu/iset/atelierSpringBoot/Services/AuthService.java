package edu.iset.atelierSpringBoot.Services;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.iset.atelierSpringBoot.Entities.User;
import edu.iset.atelierSpringBoot.Repositories.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    

    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64enocder = Base64.getUrlEncoder();
    private Object UserRipository;


    public User register(User user) {


        if(checkUserExist(user)== true)
            return null;


        user.setToken(generateToken());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);

    }

    private String generateToken() {

        byte[] token = new byte[24];
        secureRandom.nextBytes(token);
        return base64enocder.encodeToString(token);

    }

    private boolean checkUserExist(User user) {
        User existingUser = userRepository.findById(user.getId()).orElse(null);

        if(existingUser == null)
            return false;
        return true;
    }

    public User login(User user) {
        Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            if (passwordEncoder.matches(user.getPassword(), existingUser.getPassword())
                    && (existingUser.getActive()!=null || existingUser.getActive()!=false)) {
                existingUser.setPassword("");
                return existingUser;
            }
        }
        return null;
    }

    public List<User> getAllAdmins() {
        return userRepository.findAll();
    }


    /* public User updateUser(User user) {
        Optional<User> u = userRepository.findById((Long) user.getId());

        if(u.isPresent()) {
            return userRepository.save(user);}
        else {
            return null ;
        }}*/
 
   
   
   
   public User updateUser(User user) {
        Optional<User> u = userRepository.findById(user.getId());

        if (u.isPresent()) {
            User existingUser = u.get();

            if (!user.getPassword().isEmpty()) {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String encodedPassword = passwordEncoder.encode(user.getPassword());
                existingUser.setPassword(encodedPassword);
            }

            existingUser.setUsername(user.getUsername());
            existingUser.setLocalisation(user.getLocalisation());
            existingUser.setEmail(user.getEmail());
            existingUser.setnum_tel(user.getnum_tel());
            existingUser.setRole(user.getRole());
            existingUser.setToken(user.getToken());
            existingUser.setActive(user.getActive());

            return userRepository.save(existingUser);
        } else {
            return null;
        }
    }
   
   




   
   
   
   
   
   
   
   
    
   /*public User updateUser(User user) {
        Optional<User> u = userRepository.findById((Long) user.getId());

        if (u.isPresent()) {
            User existingUser = u.get();
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(passwordEncoder.encode(user.getPassword())); // encode the new password
            existingUser.setActive(user.getActive());
            return userRepository.save(existingUser);
        } else {
            return null;
        }
    }   */

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }


    public User findUserById(Long id) {


            Optional<User> u = userRepository.findById(id);
            if (u.isPresent()) {
                return u.get();
            } else {
                return null;
            }

        }


    public List<User> findAllUserByActif(Boolean active) {
        return userRepository.findByActive(active);

    }
    
    public List<User> findInactiveUsers() {
        List<User> inactiveUsers = userRepository.findByActiveIsNull();
        return inactiveUsers;
    }
    
    
    public List<User> findActiveUsers() {
        List<User> inactiveUsers = userRepository.findAllByActiveTrue();
        return inactiveUsers;
    }
    
    
    public Long countactiveUsers() {
        return userRepository.countactiveUsers();
    }
    
    public Long countInactiveUsers() {
        return userRepository.countInactiveUsers();
    }


    public Boolean findUserByUsername(Long id) {
        Optional<User> u = userRepository.findById(id);
        if (u.isPresent()) {
            return u.get().getActive();
        } else {
            return null;
        }

    }

    
    
  
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
 
    
  
    
    public List<User> findByLocalisation(String localisation) {
        return userRepository.findByLocalisation(localisation);
    }
    



    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    
    
   
    
    
    
}

