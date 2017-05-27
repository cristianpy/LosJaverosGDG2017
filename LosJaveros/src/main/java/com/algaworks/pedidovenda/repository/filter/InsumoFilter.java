package com.algaworks.pedidovenda.repository.filter;

import java.io.Serializable;

import javax.persistence.Id;

public class InsumoFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;

	
@Id
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}