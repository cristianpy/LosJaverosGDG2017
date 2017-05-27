package com.algaworks.pedidovenda.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "archivo")
public class ItemVueloArchivo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	
	private Archivo Archivo;
	
	private Vuelo vuelo;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemva_seq")
	@SequenceGenerator(name = "itemva_seq", sequenceName = "itemva_seq", allocationSize=1)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	
	@ManyToOne
	@JoinColumn(name = "vuelo_id", nullable = false)
	public Vuelo getVuelo() {
		return vuelo;
	}

	public void setVuelo(Vuelo vuelo) {
		this.vuelo = vuelo;
	}

	@ManyToOne
	@JoinColumn(name = "Archivo_id", nullable = false)
	public Archivo getArchivo() {
		return Archivo;
	}

	public void setArchivo(Archivo Archivo) {
		this.Archivo = Archivo;
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
		ItemVueloArchivo other = (ItemVueloArchivo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Transient
	public boolean isArchivoAssociado() {
		return this.getArchivo() != null && this.getArchivo().getId() != null;
	}

}
