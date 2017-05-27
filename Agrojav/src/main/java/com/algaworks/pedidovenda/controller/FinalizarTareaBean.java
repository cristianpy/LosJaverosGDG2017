package com.algaworks.pedidovenda.controller;

import java.io.Serializable;

import javax.enterprise.event.Event;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Tarea;
import com.algaworks.pedidovenda.service.FinalizarTareaService;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@RequestScoped
public class FinalizarTareaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FinalizarTareaService finalizarTareaService;
	
	@Inject
	@TareaEdicao
	private Tarea tarea;
	
	@Inject
	private Event<TareaAlteradoEvent> tareaAlteradoEvent;
	
	public void emitirTarea() {
		this.tarea.removerItemVazio();
		
		try {
			this.tarea = this.finalizarTareaService.emitir(this.tarea);
			this.tareaAlteradoEvent.fire(new TareaAlteradoEvent(this.tarea));
			
			FacesUtil.addInfoMessage("Tarea finalizada!");
		} finally {
			this.tarea.adicionarItemVazio();
		}
	}
	
}
