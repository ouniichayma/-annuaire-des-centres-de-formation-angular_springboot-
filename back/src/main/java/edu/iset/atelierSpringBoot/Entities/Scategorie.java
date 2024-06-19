package edu.iset.atelierSpringBoot.Entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "scategorie")
public class Scategorie {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long id;
	  private String code;
	  private String libelle;
	  private int rang;
	  private long num_cat;
	  
	  
	  @ManyToOne
		private Categorie categorie;
	  
	  
	  @OneToMany(mappedBy = "scategorie",cascade={CascadeType.REMOVE},
			  fetch=FetchType.EAGER)
		private List<Formation> formations;
	  
	  
		public Categorie getCategorie() {
			return categorie;
		}
		
		public void setCategorie(Categorie categorie) {
			this.categorie = categorie;
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
	public long getNum_cat() {
		return num_cat;
	}
	public void setNum_cat(long num_cat) {
		this.num_cat = num_cat;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	
	
	public int getRang() {
		return rang;
	}
	public void setRang(int rang) {
		this.rang = rang;
	}

	public Scategorie(long id, String code, long num_cat, String libelle, int rang) {
		super();
		this.id = id;
		this.code = code;
		this.num_cat = num_cat;
		this.libelle = libelle;
		this.rang = rang;
		
	}
	public Scategorie() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Scategorie [id=" + id + ", code=" + code + ", num_cat=" + num_cat + ", libelle=" + libelle
				+  ", rang=" + rang +"]";
	}
}
