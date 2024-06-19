package edu.iset.atelierSpringBoot.Entities;

import java.util.ArrayList;
import java.util.List;

import edu.iset.atelierSpringBoot.Repositories.UserRepository;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Tuple;

@Entity
@Table(name = "formation")
public class Formation {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	  private long id;
	  private String code;
	  private String libelle;
	//username of centre de formation
	  private String username;
	  @Lob
	  private String description;
		//prix
	  private float pa;
		//numero de place disponnible
	  private int place;
	//ville
	  private String local;
	  private String fileName;
	  private long num_cat;
	  private long num_scat;
	 
	  @ManyToOne
	  private Scategorie scategorie;
		  
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
		
		
		
		
		
		
		
	  
	  
		public Scategorie getScategorie() {
			return scategorie;
		}
		
		public void setScategorie(Scategorie scategorie) {
			this.scategorie = scategorie;
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
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getusername() {
		return username;
	}
	public void setusername(String username) {
		this.username = username;
	}
	
	
	
	public String getdescription() {
		return description;
	}
	public void setdescription(String description) {
		this.description = description;
	}
	
	
	public float getPa() {
		return pa;
	}
	public void setPa(float pa) {
		this.pa = pa;
	}
	public int getPlace() {
		return place;
	}
	public void setPlace(int place) {
		this.place = place;
	}
	
	
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	
	
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	

	public long getNum_cat() {
		return num_cat;
	}
	public void setNum_cat(long num_cat) {
		this.num_cat = num_cat;
	}
	public long getNum_scat() {
		return num_scat;
	}
	public void setNum_scat(long num_scat) {
		this.num_scat = num_scat;
	}
	public Formation(long id, String code, String libelle,String username,String description, float pa, int place, String local, String image,long num_cat, long num_scat, String fileName) {
		super();
		this.id = id;
		this.code = code;
		this.libelle = libelle;
		this.username=username;
		this.description=description;
		this.pa = pa;

		this.place = place;
		this.local = local;
		this.fileName = fileName;
		this.num_cat = num_cat;
		this.num_scat = num_scat;
		
		
	}
	
	@Override
	public String toString() {
		return "Formation [id=" + id + ", code=" + code + ", libelle=" + libelle + ",username=" +username +",description=" +description + ", pa=" + pa + ", place=" + place
				+ ", local=" + local + ", fileName=" + fileName + ", num_cat=" + num_cat + ", num_scat=" + num_scat + "]";
	}
	public Formation() {
		super();
		// TODO Auto-generated constructor stub
	}


	  
	
}
