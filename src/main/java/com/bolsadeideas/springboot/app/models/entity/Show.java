package com.bolsadeideas.springboot.app.models.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.lang.Nullable;

@Entity
@Table(name = "shows")
public class Show{



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "show_id", nullable = false, unique = true)
	private Long id;

	@Size(min = 1, message = "Titulo es requerido")
	private String showTitle;

	@Size(min = 1, message = "Network es requerido")
	private String showNetwork;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User creatorShow;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "shows_ratings", joinColumns = @JoinColumn(name = "show_id"), inverseJoinColumns = @JoinColumn(name = "rating_id"))
	private List<Rating> ratings;
    
	@Nullable
	private String foto;
	
	
	private String descripcion;
	
	
	private String duracion;
	
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setReseña(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public User getCreatorShow() {
		return creatorShow;
	}

	public void setCreatorShow(User creatorShow) {
		this.creatorShow = creatorShow;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> list) {
		this.ratings = (List<Rating>) list;
	}

	public Show(String showTitle, String showNetwork) {
		super();
		this.showTitle = showTitle;
		this.showNetwork = showNetwork;
	}
	
	

	public Show(Long id, @Size(min = 1, message = "Titulo es requerido") String showTitle,
			@Size(min = 1, message = "Network es requerido") String showNetwork, User creatorShow) {
		super();
		this.id = id;
		this.showTitle = showTitle;
		this.showNetwork = showNetwork;
		this.creatorShow = creatorShow;
	}

	public Show() {
		super();
	}

	
	
	public Show( @Size(min = 1, message = "Titulo es requerido") String showTitle,
			@Size(min = 1, message = "Network es requerido") String showNetwork, List<Rating> ratings,
			String foto, String descripcion, String duracion) {
		super();
		
		this.showTitle = showTitle;
		this.showNetwork = showNetwork;

		this.ratings = ratings;
		this.foto = foto;
		this.descripcion = descripcion;
		this.duracion = duracion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShowTitle() {
		return showTitle;
	}

	public void setShowTitle(String showTitle) {
		this.showTitle = showTitle;
	}

	public String getShowNetwork() {
		return showNetwork;
	}

	public void setShowNetwork(String showNetwork) {
		this.showNetwork = showNetwork;
	}

	

}
