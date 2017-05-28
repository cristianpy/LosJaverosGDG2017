 package com.algaworks.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.pedidovenda.model.Parcela;
import com.algaworks.pedidovenda.repository.Parcelas;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class CadastroParcelaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Parcelas parcelas;
	
	@Transactional
	public Parcela salvar(Parcela insumo) {
		Parcela insumoExistente = parcelas.porunNome(insumo.getNome());
		
	if (insumoExistente != null && !insumoExistente.equals(insumo)){
			throw new NegocioException("Ya existe um insumo con descripcion informado.");
		}
		
		
		return parcelas.guardar(insumo);
	}

	
}
