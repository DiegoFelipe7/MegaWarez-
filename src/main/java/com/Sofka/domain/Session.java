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
@Table(name="session")
public class Session implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ses_id")
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Users.class, optional = false)
    @JoinColumn(name = "ses_usuario_id", nullable = false)
    @JsonBackReference
    private Users usuario;
    
    
    @Column(name="ses_token")
    private String token;
    
    @Column(name="ses_create_at")
    private Instant create_at;
    
    
}
