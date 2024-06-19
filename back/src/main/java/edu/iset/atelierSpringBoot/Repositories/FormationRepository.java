package edu.iset.atelierSpringBoot.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.iset.atelierSpringBoot.Entities.Formation;

@Repository
public interface FormationRepository extends JpaRepository<Formation, Long>{

	Optional<Formation> findByusername(String username);

	List<Formation> findAllByusername(String username);

	List<Formation> findByLocal(String local);
    List<Formation> findByLibelleContainingIgnoreCase(String libelle);
    List<Formation> findByUsernameContainingIgnoreCaseAndLocalContainingIgnoreCaseAndLibelleContainingIgnoreCase(String username, String local, String libelle);

    @Query("SELECT COUNT(f) FROM Formation f")
    Long countFormations();
    
}
