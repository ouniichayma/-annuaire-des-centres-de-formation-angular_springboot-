package edu.iset.atelierSpringBoot.Services;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.iset.atelierSpringBoot.Entities.Client;

import edu.iset.atelierSpringBoot.Repositories.ClientRepository;


@Service
public class AuthClientService {

	 @Autowired
	    private ClientRepository clientRepository;
	    @Autowired
	    private PasswordEncoder passwordEncoder;
	    

	    private static final SecureRandom secureRandom = new SecureRandom();
	    private static final Base64.Encoder base64enocder = Base64.getUrlEncoder();
	    private Object ClientRepository;
	    
	    
	    
	    
	    
	    public Client register(Client client) {


	        if(checkUserExist(client)== true)
	            return null;


	        client.setToken(generateToken());

	        client.setPassword(passwordEncoder.encode(client.getPassword()));
	        return clientRepository.save(client);

	    }
	    
	    
	    
	    
	    
	    private String generateToken() {

	        byte[] token = new byte[24];
	        secureRandom.nextBytes(token);
	        return base64enocder.encodeToString(token);

	    }

	    private boolean checkUserExist(Client client) {
	        Client existingClient = clientRepository.findById(client.getId()).orElse(null);

	        if(existingClient == null)
	            return false;
	        return true;
	    }
	    
	    
	    public Client login(Client client) {
	        Optional<Client> optionalClient = clientRepository.findByUsername(client.getUsername());
	        if (optionalClient.isPresent()) {
	            Client existingClient = optionalClient.get();
	            if (passwordEncoder.matches(client.getPassword(), existingClient.getPassword())
	                   ) {
	                existingClient.setPassword("");
	                return existingClient;
	            }
	        }
	        return null;
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    public Client updateClient(Client client) {
	        Optional<Client> c = clientRepository.findById(client.getId());

	        if (c.isPresent()) {
	            Client existingClient = c.get();

	            if (!client.getPassword().isEmpty()) {
	                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	                String encodedPassword = passwordEncoder.encode(client.getPassword());
	                existingClient.setPassword(encodedPassword);
	            }

	            existingClient.setUsername(client.getUsername());
	            existingClient.setPrenom(client.getPrenom());
	            existingClient.setEmail(client.getEmail());
	            existingClient.setNum_tel(client.getNum_tel());
	            
	            existingClient.setToken(client.getToken());
	           

	            return clientRepository.save(existingClient);
	        } else {
	            return null;
	        }
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    public void deleteClientById(Long id) {
	        clientRepository.deleteById(id);
	    }


	    public Client findClientById(Long id) {


	            Optional<Client> u = clientRepository.findById(id);
	            if (u.isPresent()) {
	                return u.get();
	            } else {
	                return null;
	            }

	        }


	    
	   
	    
	 
	    
	    
	  



	    
	    
	  
	    public Optional<Client> findByUsername(String username) {
	        return clientRepository.findByUsername(username);
	    }
	    
	 
	    
	  
	    
	  
	    



	    public Optional<Client> findById(Long id) {
	        return clientRepository.findById(id);
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    


}
