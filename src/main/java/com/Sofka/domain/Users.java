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
@Table(name="usuario")
public class Users implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usu_id")
    private Integer id;
    
    @Column(name="usu_username" ,nullable = false, length = 100)
    private String username;
    
    @Column(name="usu_password", nullable = false, length = 100)
    private String password;
    
    @Column(name="usu_create_at" , nullable = false)
    private Instant create_at;
    
    @Column (name="usu_update_at", nullable = false)
    private Instant update_at;
}
