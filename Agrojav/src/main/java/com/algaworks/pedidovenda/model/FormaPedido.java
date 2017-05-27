package com.algaworks.pedidovenda.model;

public enum FormaPedido { 

	
	
	LOTUS("Lotus"), 
	VERBAL("Verbal"), 
	NOTA("Nota");
	
	
	
	private String descricao;
	
	FormaPedido(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
}