 package com.algaworks.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.pedidovenda.model.Insumo;
import com.algaworks.pedidovenda.repository.Insumos;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class CadastroInsumoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Insumos insumos;
	
	@Transactional
	public Insumo salvar(Insumo insumo) {
		Insumo insumoExistente = insumos.porunNome(insumo.getNome());
		
	if (insumoExistente != null && !insumoExistente.equals(insumo)){
			throw new NegocioException("Ya existe um insumo con descripcion informado.");
		}
		
		
		return insumos.guardar(insumo);
	}

	
}
