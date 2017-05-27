package com.algaworks.pedidovenda.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_cultivo")
public class TipoCultivo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String descripcion;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_cultivo_seq")
	@SequenceGenerator(name = "tipo_cultivo_seq", sequenceName = "tipo_cultivo_seq", allocationSize=1)	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = false, length = 100)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descricao) {
		this.descripcion = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoCultivo other = (TipoCultivo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}