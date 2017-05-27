	package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import com.algaworks.pedidovenda.model.Categoria;
import com.algaworks.pedidovenda.model.Parcela;
import com.algaworks.pedidovenda.repository.Categorias;
import com.algaworks.pedidovenda.service.ParcelaService;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ParcelaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Categorias categorias;


	@Inject
	private ParcelaService ParcelaService;
	private Parcela parcela;

	

	public ParcelaBean() {
		limpiar();
	}

	public void inicializar() {
		/* System.out.println("Inicializando..."); */

		if (FacesUtil.isNotPostback()) {
			//categoriasRaizes = categorias.raizes();
			
			
		}
	}

	

	private void limpiar() {
		parcela = new Parcela();
		//categoriaP = null;
	}

	public void salvar() {
		this.parcela = (Parcela) ParcelaService.salvar(this.parcela);
		limpiar();

		FacesUtil.addInfoMessage("Parcela se agrego corectamente!");
	}

	public Parcela getParcela() {
		return parcela;
	}

	public void setParcelae(Parcela parcela) {
		this.parcela = parcela;
		if (this.parcela != null) {
			//this.categori
		}
	}

	

	public boolean isEditando() {
		return this.parcela.getId() != null;
	}

	public void setParcela(Parcela parcela) {
		this.parcela = parcela;
		if (this.parcela != null) {
			//this.categoria
		}
	}

}