package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Parcela;
import com.algaworks.pedidovenda.repository.Parcelas;
import com.algaworks.pedidovenda.repository.filter.ParcelaFilter;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaParcelasBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Parcelas parcelas;
	
	private ParcelaFilter filtro;
	private List<Parcela> parcelasFiltrados;
	
	private Parcela parcelaSelecionado;
	
	public PesquisaParcelasBean() {
		filtro = new ParcelaFilter();
	}
	
	public void excluir() {
		parcelas.remover(parcelaSelecionado);
		parcelasFiltrados.remove(parcelaSelecionado);
		
		FacesUtil.addInfoMessage("Parcela " + parcelaSelecionado.getId() 
				+ " excluído com suceso.");
	}
	
	public void pesquisar() {
		parcelasFiltrados = parcelas.filtrados(filtro);
	}
	
	public List<Parcela> getParcelasFiltrados() {
		return parcelasFiltrados;
	}

	public ParcelaFilter getFiltro() {
		return filtro;
	}

	public Parcela getParcelaSelecionado() {
		return parcelaSelecionado;
	}

	public void setParcelaSelecionado(Parcela parcelaSelecionado) {
		this.parcelaSelecionado = parcelaSelecionado;
	}
	
}