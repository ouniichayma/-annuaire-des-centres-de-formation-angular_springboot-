package edu.iset.atelierSpringBoot.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import edu.iset.atelierSpringBoot.Entities.Client;
import edu.iset.atelierSpringBoot.Entities.Demande;
import edu.iset.atelierSpringBoot.Repositories.ClientRepository;
import edu.iset.atelierSpringBoot.Repositories.DemandeRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class DemandeController {
	  @Autowired
	    private DemandeRepository demandeRepository;
		@Autowired
		ClientRepository clientRepository;
	  
	  
		
		
		
		
		
		
		
	
		
		
		
		@PostMapping("/{id}/dislike")
		public ResponseEntity<Map<String, Integer>> dislikePost(@PathVariable Long id) {
		    Optional<Demande> post = demandeRepository.findById(id);
		    if (post.isPresent()) {
		        Demande p = post.get();
		        p.setDislikes(p.getDislikes() + 1);
		        demandeRepository.save(p);
		        Map<String, Integer> response = new HashMap<>();
		        response.put("likes", p.getLikes());
		        response.put("dislikes", p.getDislikes());
		        return ResponseEntity.ok(response);
		    } else {
		        return ResponseEntity.notFound().build();
		    }
		}
		
		
		
		
		 @PostMapping("/{id}/like")
		  public ResponseEntity<Map<String, Integer>> likePost(@PathVariable Long id) {
		    Optional<Demande> post = demandeRepository.findById(id);
		    if (post.isPresent()) {
		    	Demande p = post.get();
		      p.setLikes(p.getLikes() + 1);
		      demandeRepository.save(p);
		      Map<String, Integer> response = new HashMap<>();
		      response.put("likes", p.getLikes());
		      response.put("dislikes", p.getDislikes());
		      return ResponseEntity.ok(response);
		    } else {
		      return ResponseEntity.notFound().build();
		    }
		  }
		
		
		
		
		 @GetMapping("/{id}/likesAndDislikes")
		 public ResponseEntity<Map<String, Integer>> getLikesAndDislikes(@PathVariable Long id) {
		     Optional<Demande> post = demandeRepository.findById(id);
		     if (post.isPresent()) {
		         Demande p = post.get();
		         Map<String, Integer> response = new HashMap<>();
		         response.put("likes", p.getLikes());
		         response.put("dislikes", p.getDislikes());
		         return ResponseEntity.ok(response);
		     } else {
		         return ResponseEntity.notFound().build();
		     }
		 }
		
		
		
		
		
		 @GetMapping("/demandes/count-Demandes")
		    public Long countCategories() {
		        return demandeRepository.countDemndes();
		    }
		
		
		
		
		
		
		
		
		
		
		
	
	  
	  @PostMapping("/demandes")
	  public ResponseEntity<Demande> createDemande(@RequestBody Demande demande) {
	      // Retrieve the client entity based on the username from the demande entity
	      Client client = clientRepository.findClientByUsername(demande.getUsername());
	      // Set the client entity to the demande entity
	      demande.setClient(client);
	      // Persist the demande entity to the database
	      Demande savedDemande = demandeRepository.save(demande);
	      // Return the saved demande entity with HTTP status 201 CREATED
	      return ResponseEntity.status(HttpStatus.CREATED).body(savedDemande);
	  }
	  
	  
	  
	  @GetMapping("/demandes/getall")
	    public ResponseEntity<List<Demande>> getAllDemandes() {
	        List<Demande> demandes = demandeRepository.findAll();
	        return new ResponseEntity<>(demandes, HttpStatus.OK);
	    }
	  
	  
	  
	  
	  @GetMapping("/demandes/{id}")
	    public ResponseEntity<Demande> getDemandeById(@PathVariable Long id) {
	        Optional<Demande> demande = demandeRepository.findById(id);
	        if (demande.isPresent()) {
	            return new ResponseEntity<>(demande.get(), HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	  
	  
	  
	  
	  
	   @PutMapping("/demandes/update/{id}")
	    public ResponseEntity<Demande> updateDemande(@PathVariable Long id, @RequestBody Demande updatedDemande) {
	        Optional<Demande> existingDemande = demandeRepository.findById(id);
	        if (existingDemande.isPresent()) {
	            Demande demandeToUpdate = existingDemande.get();
	            demandeToUpdate.setLibelle(updatedDemande.getLibelle());
	            demandeToUpdate.setUsername(updatedDemande.getUsername());
	            demandeToUpdate.setDescription(updatedDemande.getDescription());
	            demandeToUpdate.setLocal(updatedDemande.getLocal());
	            Demande savedDemande = demandeRepository.save(demandeToUpdate);
	            return new ResponseEntity<>(savedDemande, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	   
	   
	   
	   
	   
	   
	   @DeleteMapping("demandes/delete/{id}")
	    public ResponseEntity<Void> deleteDemande(@PathVariable Long id) {
	        Optional<Demande> demande = demandeRepository.findById(id);
	        if (demande.isPresent()) {
	            demandeRepository.deleteById(id);
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	   
	   
	  
	  
}
