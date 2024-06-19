package edu.iset.atelierSpringBoot.Entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity
public class Client {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(length = 100)
	    private String username;
	    @Column(length = 100)
	    private String prenom;

	    
	@Column(unique = true)
	    private String email;
	    private int num_tel;
	    private String password;
	    
	    @Transient
	    private String confirmPassword;
	  
	    private String token;
	    
	    private String fileName;
	    
	    
	    
	    @OneToMany(mappedBy="client")
	    private List<Demande> demandes=new ArrayList<>();
	    
	    
	    
	    @OneToMany(mappedBy="client")
	    private List<Candidate> candidates=new ArrayList<>();
	    
	    

		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPrenom() {
			return prenom;
		}

		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public int getNum_tel() {
			return num_tel;
		}

		public void setNum_tel(int num_tel) {
			this.num_tel = num_tel;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getConfirmPassword() {
			return confirmPassword;
		}

		public void setConfirmPassword(String confirmPassword) {
			this.confirmPassword = confirmPassword;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}



		@Override
		public String toString() {
			return "Client [id=" + id + ", username=" + username + ", prenom=" + prenom + ", email=" + email
					+ ", num_tel=" + num_tel + ", password=" + password + ", confirmPassword=" + confirmPassword
					+ ", token=" + token +       ", fileName='" + fileName +'\''+  "]";
		}
	    
	    
	    
	    
}
