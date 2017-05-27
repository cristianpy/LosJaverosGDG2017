 package com.algaworks.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.pedidovenda.model.Requisito;
import com.algaworks.pedidovenda.repository.Requisitos;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class CadastroRequisitoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Requisitos requisitos;
	
	@Transactional
	public Requisito salvar(Requisito requisito) {
		Requisito requisitoExistente = requisitos.porunNome(requisito.getNome());
		
	if (requisitoExistente != null && !requisitoExistente.equals(requisito)){
			throw new NegocioException("Ya existe um requisito con descripcion informado.");
		}
		
		
		return requisitos.guardar(requisito);
	}

	
}
