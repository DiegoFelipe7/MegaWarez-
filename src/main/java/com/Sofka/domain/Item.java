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
@Table(name="item")
public class Item implements Serializable {
	 private static final long serialVersionUID = 1L;
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "itm_id")
	    private Integer id;
	    /**
		  * Punto de enlace con la entidad sub categoria (una sub categoria puede tener muchos items)
		 */
	    @ManyToOne(fetch = FetchType.LAZY, targetEntity = SubCategory.class)
	    @JoinColumn(name = "itm_subcategoria_id", nullable = false)
	    @JsonBackReference
	    private SubCategory id_subcategoria;
	    
	    
	    @Column(name="itm_nombre" )
	    private String nombre;
	    
	    @Column(name="itm_status")
	    private String status;
	    
	    @Column(name="itm_created_at")
	    private Instant create_at;
	    
		
		 /**
	     * Punto de enlace entre la entidad del item y descarga (una descarga puede tener muchas items)
	     */
	   /*@OneToMany(mappedBy = "idItem",
	            targetEntity = Download.class,
	            fetch = FetchType.EAGER,
	            cascade = CascadeType.REMOVE)
	    @JsonManagedReference

	    private List<Download> item = new ArrayList<>();
	    
	   
	   
		public List<Download> getItem() {
			return item;
		}

		public void setItem(List<Download> item) {
			this.item = item;
		}*/

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public SubCategory getId_subcategoria() {
			return id_subcategoria;
		}

		public void setId_subcategoria(SubCategory id_subcategoria) {
			this.id_subcategoria = id_subcategoria;
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
