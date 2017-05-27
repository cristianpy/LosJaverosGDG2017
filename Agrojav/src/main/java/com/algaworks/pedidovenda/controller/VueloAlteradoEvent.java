package com.algaworks.pedidovenda.controller;

import com.algaworks.pedidovenda.model.Vuelo;



public class VueloAlteradoEvent {

	private Vuelo vuelo;
	
	public VueloAlteradoEvent(Vuelo vuelo) {
		this.vuelo = vuelo;
	}

	public Vuelo getVuelo() {
		return vuelo;
	}
	
}
