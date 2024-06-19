package edu.iset.atelierSpringBoot.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.iset.atelierSpringBoot.Entities.Candidate;


public interface CandidateRepository extends JpaRepository<Candidate, Long> {
	Optional<Candidate> findById(long id);
	List<Candidate> findAll();
	void deleteById(Long id);
	
	
}
