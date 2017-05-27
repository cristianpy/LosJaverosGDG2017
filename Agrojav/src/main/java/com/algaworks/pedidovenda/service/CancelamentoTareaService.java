package com.algaworks.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.pedidovenda.model.StatusTarea;
import com.algaworks.pedidovenda.model.Tarea;
import com.algaworks.pedidovenda.repository.Tareas;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class CancelamentoTareaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Tareas tareas;
	
	/*@Inject
	private EstoqueService estoqueService;*/
	
	@Transactional
	public Tarea cancelar(Tarea tarea) {
		tarea = this.tareas.porId(tarea.getId());
		
		if (tarea.isNaoCancelavel()) {
			throw new NegocioException("Tarea no pode ser cancelado estando con status "
					+ tarea.getStatus().getDescricao() + ".");
		}
		
		/*if (pedido.isEmitido()) {
			this.estoqueService.retornarItensEstoque(pedido);
		}*/
		
		tarea.setStatus(StatusTarea.CANCELADO);
		
		tarea = this.tareas.guardar(tarea);
		
		return tarea;
	}

}
