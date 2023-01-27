package com.bolsadeideas.springboot.app.models.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;



@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Long id;
	@Size(min = 3, message = "El nombre de usuario debe estar presente")
	private String username;
	
	@Email
	@Size(min = 5, message = "El email debe estar presente y en un formato válido")
	private String email;
	@Size(min = 8, message = "La Password debe estar presente y en un formato válido")
	private String password;
	@Transient
	private String passwordConfirmation;
	
	
	
	@OneToMany(mappedBy = "creatorShow", fetch = FetchType.LAZY)
	List<Show> userShows;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<Rating> ratings;

	@ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
	private List<Role> roles;

	
	public List<Show> getUserShows() {
		return userShows;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public void setUserShows(List<Show> userShows) {
		this.userShows = userShows;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public User() {
		super();
	}

	public User(String username, String email, String password, String passwordConfirmation) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.passwordConfirmation = passwordConfirmation;
	}

	

	public User(Long id, @Size(min = 3, message = "El nombre de usuario debe estar presente") String username,
			@Email @Size(min = 5, message = "El email debe estar presente y en un formato válido") String email,
			@Size(min = 8, message = "La Password debe estar presente y en un formato válido") String password,
			String passwordConfirmation) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.passwordConfirmation = passwordConfirmation;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
	
	

}
