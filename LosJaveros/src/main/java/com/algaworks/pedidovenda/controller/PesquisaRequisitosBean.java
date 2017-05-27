package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Requisito;
import com.algaworks.pedidovenda.repository.Requisitos;
import com.algaworks.pedidovenda.repository.filter.RequisitoFilter;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaRequisitosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Requisitos requisitos;
	
	private RequisitoFilter filtro;
	private List<Requisito> requisitosFiltrados;
	
	private Requisito requisitoSelecionado;
	
	public PesquisaRequisitosBean() {
		filtro = new RequisitoFilter();
	}
	
	public void excluir() {
		requisitos.remover(requisitoSelecionado);
		requisitosFiltrados.remove(requisitoSelecionado);
		
		FacesUtil.addInfoMessage("Requisito " + requisitoSelecionado.getId() 
				+ " excluído com suceso.");
	}
	
	public void pesquisar() {
		requisitosFiltrados = requisitos.filtrados(filtro);
	}
	
	public List<Requisito> getRequisitosFiltrados() {
		return requisitosFiltrados;
	}

	public RequisitoFilter getFiltro() {
		return filtro;
	}

	public Requisito getRequisitoSelecionado() {
		return requisitoSelecionado;
	}

	public void setRequisitoSelecionado(Requisito requisitoSelecionado) {
		this.requisitoSelecionado = requisitoSelecionado;
	}
	
}