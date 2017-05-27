 package com.algaworks.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.pedidovenda.model.Parcela;
import com.algaworks.pedidovenda.repository.Parcelas;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class ParcelaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Parcelas parcelas;
	
	@Transactional
	public Parcela salvar(Parcela parcela) {
		Parcela parcelaExistente = parcelas.porNombre(parcela.getDescripcion());
		
	if (parcelaExistente != null && !parcelaExistente.equals(parcela)){
			throw new NegocioException("Ya existe unaparcela con la descripcion ingresada.");
		}
		
		
		return parcelas.guardar(parcela);
	}

	
}
