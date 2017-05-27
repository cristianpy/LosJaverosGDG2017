package com.algaworks.pedidovenda.model;

public enum VantActuante { 

	
	
	EBEE1("Ebee 1 (2014)"), 
	 
	EBEE2("Ebee 2 (*)");
	
	
	
	private String descricao;
	
	VantActuante(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
}