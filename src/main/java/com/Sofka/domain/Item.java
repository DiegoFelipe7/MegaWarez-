package com.Sofka.domain;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	    
	    @Column(name="itm_nombre" ,nullable = false, length = 100)
	    private String nombre;
	    
	    @Column(name="itm_create_at" , nullable = false)
	    private Instant create_at;
	
}
