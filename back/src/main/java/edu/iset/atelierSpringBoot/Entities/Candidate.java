package edu.iset.atelierSpringBoot.Entities;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "candidate")
public class Candidate {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String prenom;
    private String secteur;
    @Lob
    @Column(name = "cv", columnDefinition = "LONGBLOB")
    private byte[] cv;
    @Lob
    @Column(name = "photo", columnDefinition = "LONGBLOB")
    private byte[] photo;
    
    
    
    @ManyToOne
	 
	  private Client client;
    
    
    
	  public void setClient(Client client) {
			this.client = client;
		}
	  
	
    
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return username;
	}
	public void setNom(String username) {
		this.username = username;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getSecteur() {
		return secteur;
	}
	public void setSecteur(String secteur) {
		this.secteur = secteur;
	}
	public byte[] getCv() {
		return cv;
	}
	public void setCv(byte[] cv) {
		this.cv = cv;
	}
	public Candidate(Long id, String username, String prenom, String secteur, byte[] cv) {
		super();
		this.id = id;
		this.username = username;
		this.prenom = prenom;
		this.secteur = secteur;
		this.cv = cv;
	}
	public Candidate() {
		
	}
	@Override
	public String toString() {
		return "Candidate [id=" + id + ", username=" + username + ", prenom=" + prenom + ", secteur=" + secteur + ", cv="
				+ Arrays.toString(cv) + "]";
	}
    
    
	
}
