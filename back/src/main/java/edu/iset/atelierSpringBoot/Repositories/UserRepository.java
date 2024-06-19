package edu.iset.atelierSpringBoot.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import edu.iset.atelierSpringBoot.Entities.User;
import jakarta.persistence.Tuple;


public interface UserRepository extends JpaRepository<User,Long>{

	 @Query("SELECT COUNT(u) FROM User u WHERE u.active = false OR u.active IS NULL")
	    Long countInactiveUsers();

	 @Query("SELECT COUNT(u) FROM User u WHERE u.active = true ")
	    Long countactiveUsers();
	 
	 
	 
	  List<User> findByUsernameContainingIgnoreCaseAndLocalisationContainingIgnoreCase(String username, String localisation);
	  List<User> findAllByusername(String username);
	 
	 
  
    List<User> findByLocalisation(String localisation);
    
    
    
    Optional<User> findByUsername(String username);
    public void deleteUserById(Long id);

 // Return a Tuple instead of a Long
    @Query("SELECT u.id FROM User u WHERE u.username = :username")
    Tuple findIdByUsername(@Param("username") String username);
    
 
    

    List<User> findByActive(Boolean active);

    Optional<User> findUserById(Long id);
    List<User> findByActiveIsNull();
    List<User> findAllByActiveTrue();
   
}
