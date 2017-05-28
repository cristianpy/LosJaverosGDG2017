package com.algaworks.pedidovenda.model;

public enum FormaPedido { 

	
	
	NORMAL("Normal"), 
	ZABRINHA("Zafriña");
	
	
	
	private String descricao;
	
	FormaPedido(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
}