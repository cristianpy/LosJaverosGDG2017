package com.algaworks.pedidovenda.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.algaworks.pedidovenda.model.StatusTarea;
import com.algaworks.pedidovenda.model.Tarea;
import com.algaworks.pedidovenda.repository.Tareas;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class CadastroTareaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Tareas tareas;
	
	@Transactional
	public Tarea salvar(Tarea tarea) 
	{
		if (tarea.isNovo()) {
			tarea.setDataCriacao(new Date());
			tarea.setStatus(StatusTarea.EN_CURSO);
		}
		if (tarea.getItens().isEmpty()) {
			throw new NegocioException("La tarea no posee item o requisito alguno.");
		}
		
		tarea = this.tareas.guardar(tarea);
		return tarea;
	}
	
}
