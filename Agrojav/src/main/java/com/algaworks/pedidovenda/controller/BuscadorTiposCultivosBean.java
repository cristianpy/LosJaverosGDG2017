package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.TipoCultivo;
import com.algaworks.pedidovenda.repository.TiposCultivos;
import com.algaworks.pedidovenda.repository.filter.TipoCultivoFilter;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class BuscadorTiposCultivosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private TiposCultivos tipoCultivos;
	
	private TipoCultivoFilter filtro;
	private List<TipoCultivo> tipoCultivosFiltrados;
	
	private TipoCultivo tipoCultivoSelecionado;
	
	public BuscadorTiposCultivosBean() {
		filtro = new TipoCultivoFilter();
	}
	
	public void excluir() {
		tipoCultivos.remover(tipoCultivoSelecionado);
		tipoCultivosFiltrados.remove(tipoCultivoSelecionado);
		
		FacesUtil.addInfoMessage("TipoCultivo " + tipoCultivoSelecionado.getId() 
				+ " excluído com suceso.");
	}
	
	public void pesquisar() {
		tipoCultivosFiltrados = tipoCultivos.filtrados(filtro);
	}
	
	public List<TipoCultivo> getTiposCultivosFiltrados() {
		return tipoCultivosFiltrados;
	}

	public TipoCultivoFilter getFiltro() {
		return filtro;
	}

	public TipoCultivo getTipoCultivoSelecionado() {
		return tipoCultivoSelecionado;
	}

	public void setTipoCultivoSelecionado(TipoCultivo tipoCultivoSelecionado) {
		this.tipoCultivoSelecionado = tipoCultivoSelecionado;
	}
	
}