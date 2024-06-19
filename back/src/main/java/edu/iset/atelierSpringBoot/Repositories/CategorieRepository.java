package edu.iset.atelierSpringBoot.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.iset.atelierSpringBoot.Entities.Categorie;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long>{
	 @Query("SELECT COUNT(c) FROM Categorie c")
	    Long countCategories();
	 @Query("SELECT c FROM Categorie c WHERE c.libelle LIKE %:libelle%")
	 List<Categorie> findByLibelleContainingIgnoreCase(String libelle);
}
