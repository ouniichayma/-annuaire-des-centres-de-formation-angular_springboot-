package edu.iset.atelierSpringBoot.Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String username;
    @Column(length = 100)
    private String localisation;
    
    @Lob // Use the @Lob annotation to create a TEXT column for the description field
    private String description;

    
@Column(unique = true)
    private String email;
    private int num_tel;
    private String password;
    
    @Transient
    private String confirmPassword;
    
    private String role;
    private String token;

    private Boolean active;
    
    private String fileName;
    
    
    @OneToMany(mappedBy="user")
    private List<Formation> formations=new ArrayList<>();
    
    
    @OneToMany(mappedBy="user")
    private List<Team> teams=new ArrayList<>();
    
    

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
    
    

    public User() {
    }

  
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public int getnum_tel() {
        return num_tel;
    }

    public void setnum_tel(int num_tel) {
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
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                  ", localisation='" + localisation + '\'' +
                ", email='" + email + '\'' +
                 ", num_tel='" + num_tel + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", fileName='" + fileName +'\''+
                ", token='" + token + '\'' +
                ", active=" + active +
                '}';
    }
    



 
    
}
