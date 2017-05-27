	package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.algaworks.pedidovenda.model.TipoCultivo;
import com.algaworks.pedidovenda.service.TipoCultivoService;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class TipoCultivoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TipoCultivoService tipoCultivoService;

	private TipoCultivo tipoCultivo;

	public TipoCultivoBean() {
		limpar();
	}

	public void inicializar() {
		System.out.println("Inicializando tipo cultivo bean...");
	}

	private void limpar() {
		tipoCultivo = new TipoCultivo();
	}

	public void salvar() {
		this.tipoCultivo = (TipoCultivo) tipoCultivoService.salvar(this.tipoCultivo);
		limpar();

		FacesUtil.addInfoMessage("Tipo de cultivo se agregó corectamente!");
	}

	public TipoCultivo getTipoCultivo() {
		return tipoCultivo;
	}

	public void setTipoCultivoe(TipoCultivo tipoCultivo) {
		this.tipoCultivo = tipoCultivo;
	}
	public boolean isEditando() {
		return this.tipoCultivo.getId() != null;
	}

	public void setTipoCultivo(TipoCultivo tipoCultivo) {
		this.tipoCultivo = tipoCultivo;
	}

}