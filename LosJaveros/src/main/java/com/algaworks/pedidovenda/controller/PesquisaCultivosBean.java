package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Cultivo;
import com.algaworks.pedidovenda.repository.Cultivos;
import com.algaworks.pedidovenda.repository.filter.CultivoFilter;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaCultivosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Cultivos cultivos;
	
	private CultivoFilter filtro;
	private List<Cultivo> cultivosFiltrados;
	
	private Cultivo cultivoSelecionado;
	
	public PesquisaCultivosBean() {
		filtro = new CultivoFilter();
	}
	
	public void excluir() {
		cultivos.remover(cultivoSelecionado);
		cultivosFiltrados.remove(cultivoSelecionado);
		
		FacesUtil.addInfoMessage("Cultivo " + cultivoSelecionado.getId() 
				+ " excluído com suceso.");
	}
	
	public void pesquisar() {
		cultivosFiltrados = cultivos.filtrados(filtro);
	}
	
	public List<Cultivo> getCultivosFiltrados() {
		return cultivosFiltrados;
	}

	public CultivoFilter getFiltro() {
		return filtro;
	}

	public Cultivo getCultivoSelecionado() {
		return cultivoSelecionado;
	}

	public void setCultivoSelecionado(Cultivo cultivoSelecionado) {
		this.cultivoSelecionado = cultivoSelecionado;
	}
	
}