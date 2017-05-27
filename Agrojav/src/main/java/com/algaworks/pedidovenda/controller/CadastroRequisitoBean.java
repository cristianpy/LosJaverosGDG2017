	package com.algaworks.pedidovenda.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Requisito;
import com.algaworks.pedidovenda.service.CadastroRequisitoService;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroRequisitoBean implements Serializable {

	private static final long serialVersionUID = 1L;


	private Requisito requisito;
	
	@Inject
	private CadastroRequisitoService cadastroRequisitoService;

	

	public CadastroRequisitoBean() {
		limpar();
	}

	public void inicializar() {
		/* System.out.println("Inicializando..."); */

		if (FacesUtil.isNotPostback()) {
			}
		}
	

	

	private void limpar() {
		requisito = new Requisito();
		
	}

	public void salvar() {
		this.requisito = (Requisito) cadastroRequisitoService.salvar(this.requisito);
		limpar();

		FacesUtil.addInfoMessage("Requisito se agrego corectamente!");
	}

	public Requisito getRequisito() {
		return requisito;
	}

	
		
	
	public boolean isEditando() {
		return this.requisito.getId() != null;
	}

	public void setRequisito(Requisito requisito) {
		this.requisito = requisito;
		
	}

}