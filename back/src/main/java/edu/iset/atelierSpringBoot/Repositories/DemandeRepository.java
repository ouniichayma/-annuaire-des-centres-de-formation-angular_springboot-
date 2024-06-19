package edu.iset.atelierSpringBoot.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.iset.atelierSpringBoot.Entities.Demande;

public interface DemandeRepository extends JpaRepository<Demande, Long>  {
	 @Query("SELECT COUNT(d) FROM Demande d")
	    Long countDemndes();
}
