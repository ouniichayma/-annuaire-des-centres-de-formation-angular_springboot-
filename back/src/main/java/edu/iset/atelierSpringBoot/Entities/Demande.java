package edu.iset.atelierSpringBoot.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "demande")
public class Demande {
	
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	  private long id;
	 
	  private String libelle;
	//username of centre de formation
	  private String username;
	  @Lob
	  private String description;
	
	
	//ville
	  private String local;
	  @ManyToOne
		 
	  private Client client;
	  
	  private int likes;
	    private int dislikes;
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    public int getLikes() {
	        return likes;
	    }

	    public void setLikes(int likes) {
	        this.likes = likes;
	    }

	    public int getDislikes() {
	        return dislikes;
	    }

	    public void setDislikes(int dislikes) {
	        this.dislikes = dislikes;
	    } 
	    
	    
	    
	    
	    
	    
	  
	  public void setClient(Client client) {
			this.client = client;
		}
	  
	  

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getLibelle() {
		return libelle;
	}


	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getLocal() {
		return local;
	}


	public void setLocal(String local) {
		this.local = local;
	}


	public Demande(long id, String libelle, String username, String description, String local) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.username = username;
		this.description = description;
		this.local = local;
	}


	@Override
	public String toString() {
		return "Demande [id=" + id + ", libelle=" + libelle + ", username=" + username + ", description=" + description
				+ ", local=" + local + "]";
	}
	public Demande() {
	}
	  
	  

}
