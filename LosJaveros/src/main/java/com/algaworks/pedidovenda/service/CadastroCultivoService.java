 package com.algaworks.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.pedidovenda.model.Cultivo;
import com.algaworks.pedidovenda.repository.Cultivos;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class CadastroCultivoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Cultivos insumos;
	
	@Transactional
	public Cultivo salvar(Cultivo insumo) {
		Cultivo insumoExistente = insumos.porunNome(insumo.getNome());
		
	if (insumoExistente != null && !insumoExistente.equals(insumo)){
			throw new NegocioException("Ya existe um insumo con descripcion informado.");
		}
		
		
		return insumos.guardar(insumo);
	}

	
}
