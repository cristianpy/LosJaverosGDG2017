package com.algaworks.pedidovenda.repository.filter;

import java.io.Serializable;

//import com.algaworks.pedidovenda.validation.SKU;

public class FuncionarioFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String nombre;

	//@SKU
	public String getId() {
		return id;
}

	public void setId(String id) {
		this.id = id == null ? null : id.toUpperCase();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}