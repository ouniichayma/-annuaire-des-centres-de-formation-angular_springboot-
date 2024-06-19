package edu.iset.atelierSpringBoot.Controller;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
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
import edu.iset.atelierSpringBoot.Entities.Client;
import edu.iset.atelierSpringBoot.Entities.User;
import edu.iset.atelierSpringBoot.Repositories.ClientRepository;
import edu.iset.atelierSpringBoot.Services.AuthClientService;
import edu.iset.atelierSpringBoot.exeption.ResourceNotFoundException;
import jakarta.servlet.ServletContext;

@RestController
@RequestMapping("/authclient")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthClientController {
	 @Autowired
	    private AuthClientService authclientService;
	 ClientRepository clientRepository;
	 @Autowired  ServletContext context;

	

	
	
	
	
	

	   /* @PostMapping("/register")
	    public Client Register(@RequestBody Client client) {

	        return  authclientService.register(client);

	        
	   
	    }*/
	 
	 
	 
	 
		
		@PostMapping("/register")
		 public ResponseEntity<Response> createClient (@RequestParam("file") MultipartFile file,
				 @RequestParam("client") String client) throws JsonParseException , JsonMappingException , Exception
		 {
			 System.out.println("Ok .............");
	       Client clie = new ObjectMapper().readValue(client, Client.class);
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

	      
	       clie.setFileName(newFileName);
	       Client cli = authclientService.register(clie);
	       if (cli != null)
	       {
	       	return new ResponseEntity<Response>(new Response (""),HttpStatus.OK);
	       }
	       else
	       {
	       	return new ResponseEntity<Response>(new Response ("Client not saved"),HttpStatus.BAD_REQUEST);	
	       }
		 }
		
		 @GetMapping(path="/Imgarticles/{id}")
		 public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
			 Client Client   = authclientService.findById(id).get();
			 return Files.readAllBytes(Paths.get(context.getRealPath("/Images/")+Client.getFileName()));
		 }
	
	
	
	
	
	
	
	 
	 
	    @PostMapping("/login")
	    public Client login(@RequestBody Client client) {

	        return authclientService.login(client);
	    }
	 
	 
	    
	    
	    
	 
	    @PutMapping(path = "/update")
	    public Client updateClient(@RequestBody Client client) {
	        return authclientService.updateClient(client);
	    }
	    @DeleteMapping(path ="delete/{id}")
	    public void DeleteClient(@PathVariable Long id) {
	        authclientService.deleteClientById(id);
	    }
	 
	 
	    
	    
	    
	    
	    
	    @GetMapping(path ="/FindById/{id}")
			public ResponseEntity<Client> findById(@PathVariable(value = "id") Long id)
					throws ResourceNotFoundException {
			 Client Client =authclientService.findById(id)
						.orElseThrow(() -> new ResourceNotFoundException("client not found for this id :: " + id));
				return ResponseEntity.ok().body(Client);
			}

		
		    
		    
		    @GetMapping(path = "/findByUsername/{username}")
		    public ResponseEntity<Client> findByUsername(@PathVariable String username) {
		        Optional<Client> client = authclientService.findByUsername(username);
		        return client.map(ResponseEntity::ok)
		                .orElseGet(() -> ResponseEntity.notFound()
		                        .build());
		    }
	    
	
	
}
