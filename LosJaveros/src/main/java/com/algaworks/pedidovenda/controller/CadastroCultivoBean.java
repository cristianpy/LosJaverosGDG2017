	package com.algaworks.pedidovenda.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Cultivo;
import com.algaworks.pedidovenda.service.CadastroCultivoService;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroCultivoBean implements Serializable {

	private static final long serialVersionUID = 1L;


	private Cultivo cultivo;
	
	@Inject
	private CadastroCultivoService cadastroCultivoService;

	

	public CadastroCultivoBean() {
		limpar();
	}

	public void inicializar() {
		/* System.out.println("Inicializando..."); */

		if (FacesUtil.isNotPostback()) {
			}
		}
	

	

	private void limpar() {
		cultivo = new Cultivo();
		
	}

	public void salvar() {
		this.cultivo = (Cultivo) cadastroCultivoService.salvar(this.cultivo);
		limpar();

		FacesUtil.addInfoMessage("Cultivo se agrego corectamente!");
	}

	public Cultivo getCultivo() {
		return cultivo;
	}

	
		
	
	public boolean isEditando() {
		return this.cultivo.getId() != null;
	}

	public void setCultivo(Cultivo cultivo) {
		this.cultivo = cultivo;
		
	}

}