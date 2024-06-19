package edu.iset.atelierSpringBoot.Controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.iset.atelierSpringBoot.Entities.Categorie;
import edu.iset.atelierSpringBoot.Entities.Scategorie;
import edu.iset.atelierSpringBoot.Repositories.ScategorieRepository;
import edu.iset.atelierSpringBoot.exeption.ResourceNotFoundException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ScategorieController {

	

	
	@Autowired 
	 ScategorieRepository  repository;
	
	
	
	
	
	
	
	  @GetMapping("/Scategories/count-Scategorie")
	    public Long countScategories() {
	        return repository.countScategories();
	    }
	
	
	
	 @GetMapping("/Scategories")
	  public List<Scategorie> getAllScategories() {
	     System.out.println("Get all Scategories...");
	 
	    List<Scategorie> Scategories = new ArrayList<>();
	    repository.findAll().forEach(Scategories::add);
	 
	    return Scategories;
	  }
	 


	 @GetMapping("/Scategories/5/{num_cat}")
		
	    public ResponseEntity<List<Scategorie>> listCateg(@PathVariable long num_cat) {
	        
			List<Scategorie> scategories = repository.findByCateg(num_cat);
	       
	        return new ResponseEntity<List<Scategorie>>(scategories, HttpStatus.OK);
	    }
	
	@GetMapping("/Scategories/{code}")
	public ResponseEntity<Scategorie> getScategorieByCode(@PathVariable String code)
			throws ResourceNotFoundException {
		Scategorie Scategorie = repository.findByCode(code)
				.orElseThrow(() -> new ResourceNotFoundException("Scategorie not found for this id :: " + code));
		return ResponseEntity.ok().body(Scategorie);
	}
 
	
	 
	@PostMapping("/Scategories")
	public Scategorie createScategorie(@Validated @RequestBody Scategorie Scategorie) {
		return repository.save(Scategorie);
	}
				

	@DeleteMapping("/Scategories/{id}")
	public Map<String, Boolean> deleteScategorie(@PathVariable(value = "id") Long ScategorieId)
			throws ResourceNotFoundException {
		Scategorie Scategorie = repository.findById(ScategorieId)
				.orElseThrow(() -> new ResourceNotFoundException("Scategorie not found  id :: " + ScategorieId));
		repository.delete(Scategorie);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  	 
	@DeleteMapping("/Scategories/delete")
	  public ResponseEntity<String> deleteAllScategories() {
	    System.out.println("Delete All Scategories...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Scategories have been deleted!", HttpStatus.OK);
	}

	  @PutMapping("/Scategories/{id}")
	  public ResponseEntity<Scategorie> updateScategorie(@PathVariable("id") long id, @RequestBody Scategorie Scategorie) {
	    System.out.println("Update Scategorie with ID = " + id + "...");
	    Optional<Scategorie> ScategorieInfo = repository.findById(id);
	    if (ScategorieInfo.isPresent()) {
	    	Scategorie scategorie = ScategorieInfo.get();
	    	scategorie.setCode(Scategorie.getCode());
	    	scategorie.setLibelle(Scategorie.getLibelle());
	    	scategorie.setNum_cat(Scategorie.getNum_cat());
	    
	      return new ResponseEntity<>(repository.save(Scategorie), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  
	  
	  @PatchMapping("/Scategories/5/{id}")
	  public ResponseEntity<Scategorie> updateScategorie(@PathVariable("id") String id) {
	    System.out.println("Update SCategorie with Code  = " + id + "...");
	 
	    Optional<Scategorie> ScategorieInfo = repository.findByCode(id);
	 
	    if (ScategorieInfo.isPresent()) {
	    	Scategorie scategorie = ScategorieInfo.get();
	           scategorie.setRang(scategorie.getRang()+1);
	         
	      return new ResponseEntity<>(repository.save(scategorie), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  
	  
	
}
