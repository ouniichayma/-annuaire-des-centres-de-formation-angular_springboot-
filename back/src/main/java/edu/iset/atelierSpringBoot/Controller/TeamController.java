package edu.iset.atelierSpringBoot.Controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import edu.iset.atelierSpringBoot.Entities.Team;
import edu.iset.atelierSpringBoot.Repositories.ScategorieRepository;
import edu.iset.atelierSpringBoot.Repositories.TeamRepository;
import edu.iset.atelierSpringBoot.Repositories.UserRepository;
import edu.iset.atelierSpringBoot.exeption.ResourceNotFoundException;
import jakarta.persistence.Tuple;
import jakarta.servlet.ServletContext;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")

public class TeamController {
	
	@Autowired 
	 TeamRepository  repository;
	
	@Autowired  ServletContext context;
	@Autowired
	private UserRepository userRepository;

	
	 
		@PostMapping("/teams")
		 public ResponseEntity<Response> createTeam (@RequestParam("file") MultipartFile file,
				 @RequestParam("team") String team) throws JsonParseException , JsonMappingException , Exception
		 {
			 System.out.println("Ok .............");
	        Team tea = new ObjectMapper().readValue(team, Team.class);
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

	       
	        tea.setFileName(newFileName);
	        Team te = repository.save(tea);
	        if (te != null)
	        {
	        	return new ResponseEntity<Response>(new Response (""),HttpStatus.OK);
	        }
	        else
	        {
	        	return new ResponseEntity<Response>(new Response ("Article not saved"),HttpStatus.BAD_REQUEST);	
	        }
		 }
		
		
		
		
		
		 @GetMapping(path="/ImgTeams/{id}")
		 public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
			 Team Team   = repository.findById(id).get();
			 return Files.readAllBytes(Paths.get(context.getRealPath("/Images/")+Team.getFileName()));
		 }
		 
		 
		 
		 
		 
		 
		 
		 @GetMapping("/teams")
		 	  public List<Team> getAllTeams() {
		 	     System.out.println("Get all Teams...");
		 	 
		 	    List<Team> Teams = new ArrayList<>();
		 	    repository.findAll().forEach(Teams::add);
		 	 
		 	    return Teams;
		 	  }
		 	 
		 @GetMapping("/teams/{id}")
		 		public ResponseEntity<Team> getTeamById(@PathVariable(value = "id") Long TeamId)
		 				throws ResourceNotFoundException {
		 		 Team Team = repository.findById(TeamId)
		 					.orElseThrow(() -> new ResourceNotFoundException("team not found for this id :: " + TeamId));
		 			return ResponseEntity.ok().body(Team);
		 		}
		 	 


		 @GetMapping("/teams/username/{username}")

		 public ResponseEntity<List<Team>> findByusername(@PathVariable(value = "username") String username)
		 		throws ResourceNotFoundException {
		  List<Team> teams = repository.findAllByusername(username);
		  if(teams.isEmpty()) {
		    throw new ResourceNotFoundException("No teams found for this username :: " + username);
		  }
		 	return ResponseEntity.ok().body(teams);
		 }





		 @GetMapping("/user/{username}/id")
		 public ResponseEntity<Long> getUserIdByUsername(@PathVariable String username) {
		     Tuple tuple = userRepository.findIdByUsername(username);
		     if (tuple == null) {
		         return ResponseEntity.notFound().build();
		     }
		     Long userId = tuple.get(0, Long.class);
		     return ResponseEntity.ok(userId);
		 }

 
		 
		 
		 @DeleteMapping("/teams/{id}")
			public Map<String, Boolean> deleteTeam(@PathVariable(value = "id") Long TeamId)
					throws ResourceNotFoundException {
				Team Team = repository.findById(TeamId)
						.orElseThrow(() -> new ResourceNotFoundException("Team not found  id :: " + TeamId));
				repository.delete(Team);
				Map<String, Boolean> response = new HashMap<>();
				response.put("deleted", Boolean.TRUE);
				return response;
			}
			  	 
			@DeleteMapping("/Teams/delete")
			  public ResponseEntity<String> deleteAllTeams() {
			    System.out.println("Delete All Teams...");
			    repository.deleteAll();
			    return new ResponseEntity<>("All Teams have been deleted!", HttpStatus.OK);
			}

			  @PutMapping("/teams/{id}")
			  public ResponseEntity<Team> updateTeam(@PathVariable("id") long id, @RequestBody Team Team) {
			    System.out.println("Update Team with ID = " + id + "...");
			    Optional<Team> teamInfo = repository.findById(id);
			    if (teamInfo.isPresent()) {
			    	Team team = teamInfo.get();
			    	team.setCode(Team.getCode());
			    	team.setNom(Team.getNom());
			    	team.setPrenom(Team.getPrenom());
			    	team.setusername(Team.getusername());
			    	team.setLocal(Team.getLocal());
			    	team.setRole(Team.getRole());
			      return new ResponseEntity<>(repository.save(Team), HttpStatus.OK);
			    } else {
			      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			    }
			  }
		 
		 
		 
		 
		 
	
	
}
