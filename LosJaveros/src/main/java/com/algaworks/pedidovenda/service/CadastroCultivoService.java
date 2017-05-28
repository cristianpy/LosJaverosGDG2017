 package com.algaworks.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.pedidovenda.model.Cultivo;
import com.algaworks.pedidovenda.repository.Cultivos;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class CadastroCultivoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Cultivos cultivos;
	
	@Transactional
	public Cultivo salvar(Cultivo cultivo) {
		Cultivo cultivoExistente = cultivos.porunNome(cultivo.getNome());
		
	if (cultivoExistente != null && !cultivoExistente.equals(cultivo)){
			throw new NegocioException("Ya existe um cultivo con descripcion informado.");
		}
		
		
		return cultivos.guardar(cultivo);
	}

	
}
