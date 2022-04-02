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

     @ManyToOne(fetch = FetchType.LAZY , targetEntity = Category.class , optional = false)
     @JoinColumn(name="scat_categoria_id")
     private SubCategory categoria;
 
	 @Column(name="scat_nombre",nullable = false, length = 100)
	 private String nombre;
	 
	 @Column(name="scat_status",nullable = false, length = 100)
	 private Integer status;
	 
	 @Column(name="scat_create_at" , nullable = false)
	 private Instant create_at;
}
