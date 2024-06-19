package edu.iset.atelierSpringBoot.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import edu.iset.atelierSpringBoot.Entities.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
	Optional<Team> findByusername(String username);

	List<Team> findAllByusername(String username);
}
