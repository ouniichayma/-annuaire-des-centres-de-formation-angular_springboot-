package edu.iset.atelierSpringBoot.Controller;




import java.util.ArrayList;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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

import org.springframework.web.bind.annotation.RestController;

import domaine.Response;
import edu.iset.atelierSpringBoot.Entities.Formation;
import edu.iset.atelierSpringBoot.Entities.User;
import edu.iset.atelierSpringBoot.Repositories.FormationRepository;
import edu.iset.atelierSpringBoot.Repositories.ScategorieRepository;
import edu.iset.atelierSpringBoot.Repositories.UserRepository;
import edu.iset.atelierSpringBoot.exeption.ResourceNotFoundException;
import jakarta.persistence.Tuple;
import jakarta.servlet.ServletContext;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.JsonMappingException;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class FormationController {
	
	
	@Autowired 
	 FormationRepository  repository;
	@Autowired 	ScategorieRepository  scatrepository;
	@Autowired  ServletContext context;
	@Autowired
	private UserRepository userRepository;
	
	
	
	
	
	
	@GetMapping("/formations/search")
	public List<Formation> searchFormations(@RequestParam(name = "username", required = false) String username,
	                                        @RequestParam(name = "local", required = false) String local,
	                                        @RequestParam(name = "libelle", required = false) String libelle) {
	    if (username != null && local != null && libelle != null) {
	        return repository.findByUsernameContainingIgnoreCaseAndLocalContainingIgnoreCaseAndLibelleContainingIgnoreCase(username, local, libelle);
	    } else if (local != null) {
	        return repository.findByLocal(local);
	    } else if (libelle != null) {
	        return repository.findByLibelleContainingIgnoreCase(libelle);
	    } else if (username != null) {
	        return repository.findAllByusername(username);
	    } else {
	        return repository.findAll();
	    }
	}
	
	
	
	
	
	  @GetMapping("/formations/count-formation")
	    public Long countFormations() {
	        return repository.countFormations();
	    }
	
	
	
	
	
	
	
	
	
	 @GetMapping(path="/Imgarticles/{id}")
	 public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
		 Formation Formation   = repository.findById(id).get();
		 return Files.readAllBytes(Paths.get(context.getRealPath("/Images/")+Formation.getFileName()));
	 }
	 
	
	 
	 
	 
	 
@GetMapping("/formations")
	  public List<Formation> getAllFormations() {
	     System.out.println("Get all Formations...");
	 
	    List<Formation> Formations = new ArrayList<>();
	    repository.findAll().forEach(Formations::add);
	 
	    return Formations;
	  }
	 
@GetMapping("/formations/{id}")
		public ResponseEntity<Formation> getFormationById(@PathVariable(value = "id") Long FormationId)
				throws ResourceNotFoundException {
		 Formation Formation = repository.findById(FormationId)
					.orElseThrow(() -> new ResourceNotFoundException("formation not found for this id :: " + FormationId));
			return ResponseEntity.ok().body(Formation);
		}
	 


@GetMapping("/formations/username/{username}")

public ResponseEntity<List<Formation>> findByusername(@PathVariable(value = "username") String username)
		throws ResourceNotFoundException {
 List<Formation> formations = repository.findAllByusername(username);
 if(formations.isEmpty()) {
   throw new ResourceNotFoundException("No formations found for this username :: " + username);
 }
	return ResponseEntity.ok().body(formations);
}





@GetMapping("/users/{username}/id")
public ResponseEntity<Long> getUserIdByUsername(@PathVariable String username) {
    Tuple tuple = userRepository.findIdByUsername(username);
    if (tuple == null) {
        return ResponseEntity.notFound().build();
    }
    Long userId = tuple.get(0, Long.class);
    return ResponseEntity.ok(userId);
}



	 
	 
	@PostMapping("/formations")
	 public ResponseEntity<Response> createArticle (@RequestParam("file") MultipartFile file,
			 @RequestParam("formation") String formation) throws JsonParseException , JsonMappingException , Exception
	 {
		 System.out.println("Ok .............");
        Formation forma = new ObjectMapper().readValue(formation, Formation.class);
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

       
        forma.setFileName(newFileName);
        Formation form = repository.save(forma);
        if (form != null)
        {
        	return new ResponseEntity<Response>(new Response (""),HttpStatus.OK);
        }
        else
        {
        	return new ResponseEntity<Response>(new Response ("Article not saved"),HttpStatus.BAD_REQUEST);	
        }
	 }
				

	@DeleteMapping("/formations/{id}")
	public Map<String, Boolean> deleteFormation(@PathVariable(value = "id") Long FormationId)
			throws ResourceNotFoundException {
		Formation Formation = repository.findById(FormationId)
				.orElseThrow(() -> new ResourceNotFoundException("Article not found  id :: " + FormationId));
		repository.delete(Formation);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  	 
	@DeleteMapping("/Formations/delete")
	  public ResponseEntity<String> deleteAllFormations() {
	    System.out.println("Delete All Formations...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Formations have been deleted!", HttpStatus.OK);
	}

	  @PutMapping("/formations/{id}")
	  public ResponseEntity<Formation> updateFormation(@PathVariable("id") long id, @RequestBody Formation Formation) {
	    System.out.println("Update Formation with ID = " + id + "...");
	    Optional<Formation> formationInfo = repository.findById(id);
	    if (formationInfo.isPresent()) {
	    	Formation formation = formationInfo.get();
	    	formation.setCode(Formation.getCode());
	    	formation.setLibelle(Formation.getLibelle());
	    	formation.setusername(Formation.getusername());
	    	formation.setdescription(Formation.getdescription());
	    	formation.setNum_cat(Formation.getNum_cat());
	    	formation.setNum_cat(Formation.getNum_scat());
	    	formation.setPa(Formation.getPa());
	    	formation.setPlace(Formation.getPlace());
	    	formation.setLocal(Formation.getLocal());
	      return new ResponseEntity<>(repository.save(Formation), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
