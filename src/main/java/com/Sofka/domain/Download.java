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
@Table(name="descarga")
public class Download implements Serializable {
	 private static final long serialVersionUID = 1L;

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "dwn_id", nullable = false)
	    private Integer id;

	    @ManyToOne(fetch = FetchType.LAZY,
	            targetEntity = Users.class,
	            optional = false)
	    @JoinColumn(name = "dwn_usuario_id")
	    @JsonBackReference
	    private Users id_usuario;

	    @ManyToOne(fetch = FetchType.LAZY,
	            targetEntity = Item.class,
	            optional = false)
	    @JoinColumn(name = "dwn_item_id")
	    @JsonBackReference
	    private Item idItem;

	    @Column(name = "dwn_created_at")
	    private Instant createdAt;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Users getId_usuario() {
			return id_usuario;
		}

		public void setId_usuario(Users id_usuario) {
			this.id_usuario = id_usuario;
		}

		public Item getIdItem() {
			return idItem;
		}

		public void setIdItem(Item idItem) {
			this.idItem = idItem;
		}

		public Instant getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(Instant createdAt) {
			this.createdAt = createdAt;
		}
	    
	    

}



