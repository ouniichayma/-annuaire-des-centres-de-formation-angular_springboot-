package edu.iset.atelierSpringBoot.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.iset.atelierSpringBoot.Entities.Client;



public interface ClientRepository  extends JpaRepository<Client,Long>{
    Optional<Client> findByUsername(String username);
    public void deleteClientById(Long id);
    Optional<Client> findClientById(Long id);
    Client findClientByUsername(String username);
}
