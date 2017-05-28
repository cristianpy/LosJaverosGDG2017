package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Insumo;
import com.algaworks.pedidovenda.repository.Insumos;
import com.algaworks.pedidovenda.repository.filter.InsumoFilter;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaCultivoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Insumos insumos;
	
	private InsumoFilter filtro;
	private List<Insumo> insumosFiltrados;
	
	private Insumo insumoSelecionado;
	
	public PesquisaCultivoBean() {
		filtro = new InsumoFilter();
	}
	
	public void excluir() {
		insumos.remover(insumoSelecionado);
		insumosFiltrados.remove(insumoSelecionado);
		
		FacesUtil.addInfoMessage("Insumo " + insumoSelecionado.getId() 
				+ " excluído com suceso.");
	}
	
	public void pesquisar() {
		insumosFiltrados = insumos.filtrados(filtro);
	}
	
	public List<Insumo> getInsumosFiltrados() {
		return insumosFiltrados;
	}

	public InsumoFilter getFiltro() {
		return filtro;
	}

	public Insumo getInsumoSelecionado() {
		return insumoSelecionado;
	}

	public void setInsumoSelecionado(Insumo insumoSelecionado) {
		this.insumoSelecionado = insumoSelecionado;
	}
	
}