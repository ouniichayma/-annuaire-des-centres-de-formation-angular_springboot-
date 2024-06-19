package edu.iset.atelierSpringBoot.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.iset.atelierSpringBoot.Entities.Scategorie;

import java.util.List;
import java.util.Optional;




@Repository
public interface ScategorieRepository extends JpaRepository<Scategorie, Long>{

	
	 @Query("SELECT t FROM Scategorie t where t.num_cat = :num_cat")
	 
	    public List<Scategorie> findByCateg(@Param("num_cat") long num_cat);
	 
	 @Query("SELECT COUNT(s) FROM Scategorie s")
	    Long countScategories();
	 
	public Optional<Scategorie> findByCode(String code);
	
	
	 @Query("SELECT sc FROM Scategorie sc WHERE sc.libelle LIKE %:libelle%")
	
	  List<Scategorie> findByLibelleContainingIgnoreCase(String libelle);
	    List<Scategorie> findByCategorieLibelleContainingIgnoreCase(String libelle);
	
	
	
}
