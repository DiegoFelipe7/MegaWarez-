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
    
    @Column(name="usu_username")
    private String username;
    
    @Column(name="usu_password")
    private String password;
    
    @Column(name="usu_created_at")
    private Instant create_at;
    
    @Column (name="usu_updated_at")
    private Instant update_at;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Instant getCreate_at() {
		return create_at;
	}

	public void setCreate_at(Instant create_at) {
		this.create_at = create_at;
	}

	public Instant getUpdate_at() {
		return update_at;
	}

	public void setUpdate_at(Instant update_at) {
		this.update_at = update_at;
	}
    
    
    
    
    
}
