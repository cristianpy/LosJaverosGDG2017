package com.algaworks.pedidovenda.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.algaworks.pedidovenda.model.StatusVuelo;
import com.algaworks.pedidovenda.model.Vuelo;
import com.algaworks.pedidovenda.repository.Vuelos;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class CadastroVueloService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Vuelos vuelos;
	
	@Transactional
	public Vuelo salvar(Vuelo vuelo) 
	{
		if (vuelo.isNovo()) {
			vuelo.setDataCriacao(new Date());
			vuelo.setStatus(StatusVuelo.EN_CAMPO);
		}
		/*if (vuelo.getItens().isEmpty()) {
			throw new NegocioException("El vuelo no posee item o requisito alguno.");
		}*/
		
		vuelo = this.vuelos.guardar(vuelo);
		return vuelo;
	}
	
}
