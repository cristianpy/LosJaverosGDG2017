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
@Table(name = "item_tarea")
public class ItemTarea implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	
	private Requisito requisito;
	
	private Tarea tarea;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemtarea_seq")
	@SequenceGenerator(name = "itemtarea_seq", sequenceName = "itemtarea_seq", allocationSize=1)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	
	@ManyToOne
	@JoinColumn(name = "tarea_id", nullable = false)
	public Tarea getTarea() {
		return tarea;
	}

	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}

	@ManyToOne
	@JoinColumn(name = "requisito_id", nullable = false)
	public Requisito getRequisito() {
		return requisito;
	}

	public void setRequisito(Requisito requisito) {
		this.requisito = requisito;
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
		ItemTarea other = (ItemTarea) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Transient
	public boolean isRequisitoAssociado() {
		return this.getRequisito() != null && this.getRequisito().getId() != null;
	}

}
