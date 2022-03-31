package com.Sofka.domain;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
	    
	    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Category.class, optional = false)
	    @JoinColumn(name = "imt_subcategoria_id", nullable = false)
	    @JsonBackReference
	    private SubCategory subcategoria;
	    
	    
	    @Column(name="itm_nombre" ,nullable = false, length = 100)
	    private String nombre;
	    
	    @Column(name="itm_create_at" , nullable = false)
	    private Instant create_at;
	
}
