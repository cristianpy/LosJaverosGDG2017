package com.algaworks.pedidovenda.model;



public enum StatusTarea {
	EN_CURSO("En curso"), 
	CONCLUIDO("Concluido"), 
	CANCELADO("Cancelado");
	private String descricao;
	
	StatusTarea(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
