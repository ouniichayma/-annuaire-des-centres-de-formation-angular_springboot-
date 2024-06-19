package edu.iset.atelierSpringBoot.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import edu.iset.atelierSpringBoot.Entities.Candidate;
import edu.iset.atelierSpringBoot.Entities.Client;
import edu.iset.atelierSpringBoot.Entities.Formation;
import edu.iset.atelierSpringBoot.Repositories.CandidateRepository;
import edu.iset.atelierSpringBoot.Repositories.ClientRepository;
import edu.iset.atelierSpringBoot.exeption.ResourceNotFoundException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CandidateController {
	
	@Autowired
    private CandidateRepository candidateRepository;
	@Autowired
	ClientRepository clientRepository;
	
	
	
	/* @PostMapping("/candidates")
	    public String addCandidate( @RequestParam("username") String username,
	                               @RequestParam("prenom") String prenom,
	                               @RequestParam("secteur") String secteur,
	                               @RequestParam("cv") MultipartFile cv,
	                               @RequestParam("photo") MultipartFile photo) throws IOException {
	        Candidate candidate = new Candidate();
	        candidate.setNom(username);
	        candidate.setPrenom(prenom);
	        candidate.setSecteur(secteur);
	        candidate.setCv(cv.getBytes());
	        candidate.setPhoto(photo.getBytes());
	        // Retrieve the client entity based on the username from the demande entity
		      Client client = clientRepository.findClientByUsername(candidate.getNom());
		      // Set the client entity to the demande entity
		      candidate.setClient(client);
	        candidateRepository.save(candidate);
	        return "redirect:/success";
	    }*/
	
	
	
	
	
	
	
	
	@PostMapping("/candidates")
	public ResponseEntity<Candidate> createCandidate(@RequestParam("username") String username,
	                                                 @RequestParam("prenom") String prenom,
	                                                 @RequestParam("secteur") String secteur,
	                                                 @RequestParam("cv") MultipartFile cv,
	                                                 @RequestParam("photo") MultipartFile photo) throws IOException {
	    Candidate candidate = new Candidate();
	    candidate.setNom(username);
	    candidate.setPrenom(prenom);
	    candidate.setSecteur(secteur);
	    candidate.setCv(cv.getBytes());
	    candidate.setPhoto(photo.getBytes());

	    // Retrieve the client entity based on the username from the demande entity
	    Client client = clientRepository.findClientByUsername(candidate.getNom());
	    // Set the client entity to the demande entity
	    candidate.setClient(client);

	    Candidate savedCandidate = candidateRepository.save(candidate);
	    return ResponseEntity.ok(savedCandidate);
	}
	
	
	
	
	
	
	
	
	@GetMapping("/candidates/{id}")
	public ResponseEntity<byte[]> getCandidatePhoto(@PathVariable Long id) {
	Optional<Candidate> candidateOptional = candidateRepository.findById(id);
	if (candidateOptional.isPresent()) {
	Candidate candidate = candidateOptional.get();
	return ResponseEntity.ok(candidate.getPhoto());
	} else {
	return ResponseEntity.notFound().build();
	}
	}
	
	
	
	
	
	
	
	
	/*@PostMapping("/candidates")
	public ResponseEntity<Candidate> createCandidate(@RequestBody Candidate candidate) {
		// Retrieve the client entity based on the username from the demande entity
	      Client client = clientRepository.findClientByUsername(candidate.getNom());
	      // Set the client entity to the demande entity
	      candidate.setClient(client);
	    Candidate savedCandidate = candidateRepository.save(candidate);
	    return ResponseEntity.ok(savedCandidate);
	}*/
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	 
	 
	
	 
	 @GetMapping("/candidate/{id}")
		public ResponseEntity<Candidate> getCandidateById(@PathVariable(value = "id") Long id)
				throws ResourceNotFoundException {
		 Candidate candidate = candidateRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Candidate not found for this id :: " + id));
			return ResponseEntity.ok().body(candidate);
		}
	
	 @GetMapping("/candidates")
	 public List<Candidate> getAllCandidates() {
	     return candidateRepository.findAll();
	 }
	 
	 
	 
	 
	
	 
	 @DeleteMapping("/candidate/{id}")
	 public ResponseEntity<Map<String, Boolean>> deleteCandidate(@PathVariable(value = "id") Long id)
	         throws ResourceNotFoundException {
	     Candidate candidate = candidateRepository.findById(id)
	             .orElseThrow(() -> new ResourceNotFoundException("Candidate not found for this id :: " + id));

	     candidateRepository.delete(candidate);
	     Map<String, Boolean> response = new HashMap<>();
	     response.put("deleted", Boolean.TRUE);
	     return ResponseEntity.ok(response);
	 }
	 
	 
	 
 

}
