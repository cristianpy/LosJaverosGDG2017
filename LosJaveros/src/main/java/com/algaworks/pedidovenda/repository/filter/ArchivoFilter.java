package com.algaworks.pedidovenda.repository.filter;

import java.io.Serializable;

import javax.persistence.Id;

public class ArchivoFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String ruta;

	
@Id
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getruta() {
		return ruta;
	}

	public void setruta(String ruta) {
		this.ruta = ruta;
	}

}