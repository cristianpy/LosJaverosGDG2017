	package com.algaworks.pedidovenda.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Insumo;
import com.algaworks.pedidovenda.service.CadastroInsumoService;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroInsumoBean implements Serializable {

	private static final long serialVersionUID = 1L;


	private Insumo insumo;
	
	@Inject
	private CadastroInsumoService cadastroInsumoService;

	

	public CadastroInsumoBean() {
		limpar();
	}

	public void inicializar() {
		/* System.out.println("Inicializando..."); */

		if (FacesUtil.isNotPostback()) {
			}
		}
	

	

	private void limpar() {
		insumo = new Insumo();
		
	}

	public void salvar() {
		this.insumo = (Insumo) cadastroInsumoService.salvar(this.insumo);
		limpar();

		FacesUtil.addInfoMessage("Insumo se agrego corectamente!");
	}

	public Insumo getInsumo() {
		return insumo;
	}

	
		
	
	public boolean isEditando() {
		return this.insumo.getId() != null;
	}

	public void setInsumo(Insumo insumo) {
		this.insumo = insumo;
		
	}

}