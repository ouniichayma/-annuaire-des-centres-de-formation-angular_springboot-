package edu.iset.atelierSpringBoot.Controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import domaine.Response;
import edu.iset.atelierSpringBoot.Entities.Formation;
import edu.iset.atelierSpringBoot.Entities.User;
import edu.iset.atelierSpringBoot.Repositories.FormationRepository;
import edu.iset.atelierSpringBoot.Repositories.ScategorieRepository;
import edu.iset.atelierSpringBoot.Repositories.UserRepository;
import edu.iset.atelierSpringBoot.Services.AuthService;
import edu.iset.atelierSpringBoot.exeption.ResourceNotFoundException;
import jakarta.servlet.ServletContext;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
	 @Autowired
	    private AuthService authService;
	 @Autowired
	 UserRepository userRepository;
	 FormationRepository  repository;
	@Autowired  ServletContext context;

	   /* @PostMapping("/register")
	    public User Register(@RequestBody User user) {

	        return  authService.register(user);

	        
	   
	    }*/
	
	
	
	
	
	
	
	
	@GetMapping("/search")
	public List<User> searchUsers(@RequestParam(name = "username", required = false) String username,
	                                        @RequestParam(name = "localisation", required = false) String localisation
	                                        ) {
	    if (username != null && localisation != null) {
	        return userRepository.findByUsernameContainingIgnoreCaseAndLocalisationContainingIgnoreCase(username, localisation);
	    } else if (localisation != null) {
	        return userRepository.findByLocalisation(localisation);
	    }else if (username != null) {
	        return userRepository.findAllByusername(username);
	    } else {
	        return userRepository.findAll();
	    }
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@PostMapping("/register")
	 public ResponseEntity<Response> createUser (@RequestParam("file") MultipartFile file,
			 @RequestParam("user") String user) throws JsonParseException , JsonMappingException , Exception
	 {
		 System.out.println("Ok .............");
       User use = new ObjectMapper().readValue(user, User.class);
       boolean isExit = new File(context.getRealPath("/Images/")).exists();
       if (!isExit)
       {
       	new File (context.getRealPath("/Images/")).mkdir();
       	System.out.println("mk dir.............");
       }
       String filename = file.getOriginalFilename();
       String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
       File serverFile = new File (context.getRealPath("/Images/"+File.separator+newFileName));
       try
       {
       	System.out.println("Image");
       	 FileUtils.writeByteArrayToFile(serverFile,file.getBytes());
       	 
       }catch(Exception e) {
       	e.printStackTrace();
       }

      
       use.setFileName(newFileName);
       User us = authService.register(use);
       if (us != null)
       {
       	return new ResponseEntity<Response>(new Response (""),HttpStatus.OK);
       }
       else
       {
       	return new ResponseEntity<Response>(new Response ("User not saved"),HttpStatus.BAD_REQUEST);	
       }
	 }
	
	 @GetMapping(path="/Imgarticles/{id}")
	 public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
		 User User   = authService.findById(id).get();
		 return Files.readAllBytes(Paths.get(context.getRealPath("/Images/")+User.getFileName()));
	 }
	 
	
	    
	   
	    

	    @PostMapping("/login")
	    public User login(@RequestBody User user) {

	        return authService.login(user);
	    }


	    @GetMapping(path = "/{active}")
	    public List<User> findUtilisateurByActif(@PathVariable Boolean active) {
	        return authService.findAllUserByActif(active);
	    }
	    
	    @GetMapping("/inactive-users")
	    public List<User> getInactiveUsers() {
	        return authService.findInactiveUsers();
	    }
	    
	    
	    @GetMapping("/active-users")
	    public List<User> getactiveUsers() {
	        return authService.findActiveUsers();
	    }
	    
	    @GetMapping("/count-inactive")
	    public Long getInactiveUsersCount() {
	        return authService.countInactiveUsers();
	    }
	    
	    @GetMapping("/count-active")
	    public Long countActiveUsers() {
	        return authService.countactiveUsers();
	    }


	    @GetMapping(path="/Alluser")
	    public List <User>getAllAdmins(){
	        return authService.getAllAdmins();
	    }
	    @PutMapping(path = "/update")
	    public User updateUser(@RequestBody User user) {
	        return authService.updateUser(user);
	    }
	    @DeleteMapping(path ="delete/{id}")
	    public void DeleteUser(@PathVariable Long id) {
	        authService.deleteUserById(id);
	    }


	   /* @GetMapping(path ="/FindById/{id}")
	    public ResponseEntity<User> findById(@PathVariable Long id) {
	        Optional<User> user = authService.findById(id);
	        return user.map(ResponseEntity::ok)

	                .orElseGet(() -> ResponseEntity.notFound()
	                        .build());

	    }*/
	    
	    @GetMapping(path ="/FindById/{id}")
		public ResponseEntity<User> findById(@PathVariable(value = "id") Long id)
				throws ResourceNotFoundException {
		 User User =authService.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + id));
			return ResponseEntity.ok().body(User);
		}

	    @GetMapping(path ="/FindBy/{id}")
	    public Boolean findByUsername(@PathVariable Long id) {
	        return authService.findUserByUsername(id);

	    }
	    
	    
	    @GetMapping(path = "/findByUsername/{username}")
	    public ResponseEntity<User> findByUsername(@PathVariable String username) {
	        Optional<User> user = authService.findByUsername(username);
	        return user.map(ResponseEntity::ok)
	                .orElseGet(() -> ResponseEntity.notFound()
	                        .build());
	    }
	    
	    @GetMapping(path = "/localisation/{localisation}")
	    public ResponseEntity<List<User>> findByLocalisation(@PathVariable String localisation) {
	        List<User> users = authService.findByLocalisation(localisation);
	        if(!users.isEmpty()) {
	            return ResponseEntity.ok(users);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	 
	    
	    
	    
	    
	    
	    
}
