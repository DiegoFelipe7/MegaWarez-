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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity
@Table(name="subcategoria")
public class SubCategory implements Serializable {
	private static final long serialVersionUID = 1L;
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "scat_id")
	 private Integer id;

     @ManyToOne(fetch = FetchType.LAZY , targetEntity = Category.class )
     @JoinColumn(name="scat_categoria_id")
     @JsonBackReference
     private Category id_categoria;
 
	 @Column(name="scat_nombre")
	 private String nombre;
	 
	 @Column(name="scat_status" )
	 private String status;
	 
	 @Column(name="scat_created_at")
	 private Instant create_at;
	 
		
   @OneToMany(mappedBy = "id_subcategoria",
            targetEntity = Item.class,
            fetch = FetchType.EAGER,
            cascade = CascadeType.REMOVE)
    @JsonManagedReference

    private List<SubCategory> item = new ArrayList<>();
   
   
   
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Category getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(Category id_categoria) {
		this.id_categoria = id_categoria;
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

	public Instant getCreate_at() {
		return create_at;
	}

	public void setCreate_at(Instant create_at) {
		this.create_at = create_at;
	}

	
	 
	 
}
