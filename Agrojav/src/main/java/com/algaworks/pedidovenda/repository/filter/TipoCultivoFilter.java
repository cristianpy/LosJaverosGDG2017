package com.algaworks.pedidovenda.repository.filter;

import java.io.Serializable;

//import com.algaworks.pedidovenda.validation.SKU;

public class TipoCultivoFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String descripcion;

	//@SKU
	public String getId() {
		return id;
}

	public void setId(String id) {
		this.id = id == null ? null : id.toUpperCase();
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}