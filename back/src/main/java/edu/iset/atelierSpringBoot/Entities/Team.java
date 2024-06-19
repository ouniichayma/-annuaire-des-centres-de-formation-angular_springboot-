package edu.iset.atelierSpringBoot.Entities;

import edu.iset.atelierSpringBoot.Repositories.UserRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Tuple;
@Entity
@Table(name = "team")
public class Team {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	  private long id;
	  private String code;
	  private String nom;
	  private String prenom;
	  private String username;
	  private String local;
	  private String role;
	  private String fileName;
	
	 
	 
		  
	  @ManyToOne
	 
	  private User user;
	  
	  
	  public User getUser() {
		    return user;
		}

		public void setUser(User user) {
		    this.user = user;
		}
		
		
		
		public Tuple getUserId(UserRepository userRepository) {
		    return userRepository.findIdByUsername(username);
		}
		
		
		
		
		
		
		
	  
	  
	  
	  
	  
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getusername() {
		return username;
	}
	public void setusername(String username) {
		this.username = username;
	}

	
	
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	


	public Team(long id, String code, String nom,String prenom,String username, String local,  String role,String image, String fileName) {
		super();
		this.id = id;
		this.code = code;
		this.nom = nom;
		this.prenom = prenom;
		this.username=username;
		this.local = local;
		this.role = role;
		this.fileName = fileName;
		
		
		
	}
	
	@Override
	public String toString() {
		return "Formation [id=" + id + ", code=" + code + ", nom=" + nom +", prenom=" + prenom + ",username=" +username + 
				 ", local=" + local + ", role=" + role + ", fileName=" + fileName + "]";
	}
	public Team() {
		super();
		// TODO Auto-generated constructor stub
	}


	  
	
}


