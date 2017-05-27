 package com.algaworks.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.pedidovenda.model.TipoCultivo;
import com.algaworks.pedidovenda.repository.TiposCultivos;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class TipoCultivoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TiposCultivos tiposCultivos;
	
	@Transactional
	public TipoCultivo salvar(TipoCultivo tipoCultivo) {
		TipoCultivo tipoCultivoExistente = tiposCultivos.porDescripcion(tipoCultivo.getDescripcion());
		
	if (tipoCultivoExistente != null && !tipoCultivoExistente.equals(tipoCultivo)){
			throw new NegocioException("Ya existe um tipo de cultivo con la descripción informada.");
		}
		
		
		return tiposCultivos.guardar(tipoCultivo);
	}

	
}
