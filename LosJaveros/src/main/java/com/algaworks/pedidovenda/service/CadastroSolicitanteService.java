 package com.algaworks.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.pedidovenda.model.Solicitante;
import com.algaworks.pedidovenda.repository.Solicitantes;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class CadastroSolicitanteService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Solicitantes solicitantes;
	
	@Transactional
	public Solicitante salvar(Solicitante solicitante) {
		Solicitante solicitanteExistente = solicitantes.porNome(solicitante.getNombre());
		
	if (solicitanteExistente != null && !solicitanteExistente.equals(solicitante)){
			throw new NegocioException("Ya existe um solicitante con nombre informado.");
		}
		
		
		return solicitantes.guardar(solicitante);
	}

	
}
