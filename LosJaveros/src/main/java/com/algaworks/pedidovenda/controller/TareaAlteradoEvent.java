package com.algaworks.pedidovenda.controller;

import com.algaworks.pedidovenda.model.Tarea;



public class TareaAlteradoEvent {

	private Tarea tarea;
	
	public TareaAlteradoEvent(Tarea tarea) {
		this.tarea = tarea;
	}

	public Tarea getTarea() {
		return tarea;
	}
	
}
