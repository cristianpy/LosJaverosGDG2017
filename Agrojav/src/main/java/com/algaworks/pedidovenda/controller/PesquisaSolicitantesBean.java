package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Solicitante;
import com.algaworks.pedidovenda.repository.Solicitantes;
import com.algaworks.pedidovenda.repository.filter.SolicitanteFilter;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaSolicitantesBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Solicitantes solicitantes;
	
	private SolicitanteFilter filtro;
	private List<Solicitante> solicitantesFiltrados;
	
	private Solicitante solicitanteSelecionado;
	
	public PesquisaSolicitantesBean() {
		filtro = new SolicitanteFilter();
	}
	
	public void excluir() {
		solicitantes.remover(solicitanteSelecionado);
		solicitantesFiltrados.remove(solicitanteSelecionado);
		
		FacesUtil.addInfoMessage("Solicitante " + solicitanteSelecionado.getId() 
				+ " excluído com suceso.");
	}
	
	public void pesquisar() {
		solicitantesFiltrados = solicitantes.filtrados(filtro);
	}
	
	public List<Solicitante> getSolicitantesFiltrados() {
		return solicitantesFiltrados;
	}

	public SolicitanteFilter getFiltro() {
		return filtro;
	}

	public Solicitante getSolicitanteSelecionado() {
		return solicitanteSelecionado;
	}

	public void setSolicitanteSelecionado(Solicitante solicitanteSelecionado) {
		this.solicitanteSelecionado = solicitanteSelecionado;
	}
	
}