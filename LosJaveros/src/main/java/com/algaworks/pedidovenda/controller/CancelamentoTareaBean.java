package com.algaworks.pedidovenda.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Tarea;
import com.algaworks.pedidovenda.service.CancelamentoTareaService;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@RequestScoped
public class CancelamentoTareaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CancelamentoTareaService cancelamentoTareaService;
	
	@Inject
	private Event<TareaAlteradoEvent> tareaAlteradoEvent;
	
	@Inject
	@TareaEdicao
	private Tarea tarea;
	
	public void cancelarTarea() {
		this.tarea = this.cancelamentoTareaService.cancelar(this.tarea);
		this.tareaAlteradoEvent.fire(new TareaAlteradoEvent(this.tarea));
		
		FacesUtil.addInfoMessage("Tarea cancelada!");
	}
	
}
