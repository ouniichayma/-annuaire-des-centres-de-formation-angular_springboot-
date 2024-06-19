package edu.iset.atelierSpringBoot.Controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale.Category;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
import edu.iset.atelierSpringBoot.Entities.Categorie;
import edu.iset.atelierSpringBoot.Entities.Formation;
import edu.iset.atelierSpringBoot.Entities.Scategorie;
import edu.iset.atelierSpringBoot.Repositories.CategorieRepository;
import edu.iset.atelierSpringBoot.Repositories.ScategorieRepository;
import edu.iset.atelierSpringBoot.exeption.ResourceNotFoundException;
import jakarta.servlet.ServletContext;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CategorieController {


	
	@Autowired 
	 CategorieRepository  repository;
	@Autowired
	ScategorieRepository scategorieRepository;
	@Autowired  ServletContext context;

	
	
	@GetMapping("/search")
	public ResponseEntity<List<Object>> searchByLibelle(@RequestParam String libelle) {
	   List<Object> result = new ArrayList<>();
	   List<Categorie> categories = repository.findByLibelleContainingIgnoreCase(libelle);
	   List<Scategorie> scategories = scategorieRepository.findByLibelleContainingIgnoreCase(libelle);
	   result.addAll(categories);
	   result.addAll(scategories);
	   return ResponseEntity.ok(result);
	}
	
	
	
	

	  @GetMapping("/Categories/count-Categorie")
	    public Long countCategories() {
	        return repository.countCategories();
	    }
	
	
	
	
	
	
	
	
	
	
	 @GetMapping("/Categories")
	  public List<Categorie> getAllCategories() {
	     System.out.println("Get all Categories...");
	 
	    List<Categorie> Categories = new ArrayList<>();
	    repository.findAll().forEach(Categories::add);
	 
	    return Categories;
	  }
	 

	 
	 @GetMapping("/Categories/{id}")
		public ResponseEntity<Categorie> getCategorieById(@PathVariable(value = "id") Long CategorieId)
				throws ResourceNotFoundException {
		 Categorie Categorie = repository.findById(CategorieId)
					.orElseThrow(() -> new ResourceNotFoundException("Categorie not found for this id :: " + CategorieId));
			return ResponseEntity.ok().body(Categorie);
		}
	 
	 
	 
	 
	 
	 @GetMapping(path="/Imgcategories/{id}")
	 public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
		 Categorie Category   = repository.findById(id).get();
		 return Files.readAllBytes(Paths.get(context.getRealPath("/Images/")+Category.getFileName()));
	 }
	 
	 
	 
	 @PostMapping("/Categories")
	 public ResponseEntity<Response> createCategory (@RequestParam("file") MultipartFile file,
			 @RequestParam("categorie") String categorie) throws JsonParseException , JsonMappingException , Exception
	 {
		 System.out.println("Ok .............");
		 Categorie categ = new ObjectMapper().readValue(categorie, Categorie.class);
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

       
        categ.setFileName(newFileName);
        Categorie cat = repository.save(categ);
        if (cat != null)
        {
        	return new ResponseEntity<Response>(new Response (""),HttpStatus.OK);
        }
        else
        {
        	return new ResponseEntity<Response>(new Response ("Article not saved"),HttpStatus.BAD_REQUEST);	
        }
	 }
	 
	
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	/*@PostMapping("/Categories")
	public Categorie createCategorie(@Valid @RequestBody Categorie Categorie) {
		return repository.save(Categorie);
	}*/
				
	
	@DeleteMapping("/Categories/{id}")
	public Map<String, Boolean> deleteCategorie(@PathVariable(value = "id") Long CategorieId)
			throws ResourceNotFoundException {
		Categorie Categorie = repository.findById(CategorieId)
				.orElseThrow(() -> new ResourceNotFoundException("Categorie not found  id :: " + CategorieId));
		repository.delete(Categorie);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  	 
	@DeleteMapping("/Categories/delete")
	  public ResponseEntity<String> deleteAllCategories() {
	    System.out.println("Delete All Categories...");
	    repository.deleteAll();
	    return new ResponseEntity<>("All Categories have been deleted!", HttpStatus.OK);
	}

	  @PutMapping("/Categories/{id}")
	  public ResponseEntity<Categorie> updateCategorie(@PathVariable("id") long id, @RequestBody Categorie Categorie) {
	    System.out.println("Update Categorie with ID = " + id + "...");
	    Optional<Categorie> CategorieInfo = repository.findById(id);
	    if (CategorieInfo.isPresent()) {
	    	Categorie categorie = CategorieInfo.get();
	    	categorie.setCode(Categorie.getCode());
	    	categorie.setLibelle(Categorie.getLibelle());
	    
	      return new ResponseEntity<>(repository.save(Categorie), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	
}
