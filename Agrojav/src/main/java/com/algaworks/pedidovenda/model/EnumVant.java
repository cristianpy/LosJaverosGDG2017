package com.algaworks.pedidovenda.model;



public enum EnumVant {
	EBEE1("Ebee 1"), 
	
	EBEE2("Ebee 2*");
	private String descricao;
	
	EnumVant(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
