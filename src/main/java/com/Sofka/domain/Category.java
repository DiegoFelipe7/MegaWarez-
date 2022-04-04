package com.Sofka.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;


@Data
@Entity
@Table(name="categoria")
public class Category implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cat_id" , nullable = false)
	private Integer id;
	
	@Column(name="cat_nombre")
	private String nombre;
	
	@Column(name="cat_status")
	private String status;
	
	
	@Column(name="cat_created_at" )
	private Instant createdAt;
	
	 /**
     * Punto de enlace entre la entidad del Categoria y Subcategoria (una categoria puede tener muchas subcategorias)
     */
   @OneToMany(mappedBy = "id_categoria",
            targetEntity = SubCategory.class,
            fetch = FetchType.EAGER,
            cascade = CascadeType.REMOVE)
    @JsonManagedReference

    private List<SubCategory> subcategorias = new ArrayList<>();
  
   
   
	public List<SubCategory> getSubcategorias() {
	return subcategorias;
	}


	public void setSubcategorias(List<SubCategory> subcategorias) {
		this.subcategorias = subcategorias;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Instant getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	
	

}
