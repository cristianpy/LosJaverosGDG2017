package com.algaworks.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.pedidovenda.model.StatusTarea;
import com.algaworks.pedidovenda.model.Tarea;
import com.algaworks.pedidovenda.repository.Tareas;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class FinalizarTareaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroTareaService cadastroTareaService;
	
	
	
	@Inject
	private Tareas tareas;
	
	@Transactional
	public Tarea emitir(Tarea tarea) {
		tarea = this.cadastroTareaService.salvar(tarea);
		
		if (tarea.isNaoEmissivel()) {
			throw new NegocioException("Tarea no puede ser concluida estando en Status "
					+ tarea.getStatus().getDescricao() + ".");
		}
		
	
		
		tarea.setStatus(StatusTarea.CONCLUIDO);
		
		tarea = this.tareas.guardar(tarea);
		
		return tarea;
	}
	
}
