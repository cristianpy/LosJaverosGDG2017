	package com.algaworks.pedidovenda.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Parcela;
import com.algaworks.pedidovenda.service.CadastroParcelaService;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroParcelaBean implements Serializable {

	private static final long serialVersionUID = 1L;


	private Parcela parcela;
	
	@Inject
	private CadastroParcelaService cadastroParcelaService;

	

	public CadastroParcelaBean() {
		limpar();
	}

	public void inicializar() {

		if (FacesUtil.isNotPostback()) {
			}
		}
	

	

	private void limpar() {
		parcela = new Parcela();
		
	}

	public void salvar() {
		this.parcela = (Parcela) cadastroParcelaService.salvar(this.parcela);
		limpar();

		FacesUtil.addInfoMessage("Parcela se agrego corectamente!");
	}

	public Parcela getParcela() {
		return parcela;
	}

	
		
	
	public boolean isEditando() {
		return this.parcela.getId() != null;
	}

	public void setParcela(Parcela parcela) {
		this.parcela = parcela;
		
	}

}