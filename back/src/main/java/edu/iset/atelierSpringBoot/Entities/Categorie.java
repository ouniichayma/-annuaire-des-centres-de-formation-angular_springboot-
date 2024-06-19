package edu.iset.atelierSpringBoot.Entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categorie")
public class Categorie {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long id;
	  private String code;
	  private String libelle;
	  private String fileName;
	 
	  @OneToMany(mappedBy = "categorie",cascade={CascadeType.REMOVE},
			  fetch=FetchType.EAGER)
		private List<Scategorie> scategories;
	  
	  
		
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
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
	public Categorie(long id, String code, String libelle) {
		super();
		this.id = id;
		this.code = code;
		this.libelle = libelle;
	}
	public Categorie() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Categorie [id=" + id + ", fileName=" + fileName + ", code=" + code + ", libelle=" + libelle + "]";
	}
}
