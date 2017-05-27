package com.algaworks.pedidovenda.model;

public enum StatusVuelo {

	EN_CAMPO("En curso"), 
	EN_POSPROCESO("En posproceso"), 
	CONCLUIDO("Concluido");
	private String descricao;
	
	StatusVuelo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
