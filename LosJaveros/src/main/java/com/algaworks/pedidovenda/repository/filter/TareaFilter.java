package com.algaworks.pedidovenda.repository.filter;

import java.io.Serializable;
import java.util.Date;

import com.algaworks.pedidovenda.model.StatusTarea;

public class TareaFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long numeroDe;
	private Long numeroAte;
	private Date dataCriacaoDe;
	private Date dataCriacaoAte;
	private String nomeParcela;
	private String nomeCultivo;
	private StatusTarea[] statuses;

	public Long getNumeroDe() {
		return numeroDe;
	}

	public void setNumeroDe(Long numeroDe) {
		this.numeroDe = numeroDe;
	}

	public Long getNumeroAte() {
		return numeroAte;
	}

	public void setNumeroAte(Long numeroAte) {
		this.numeroAte = numeroAte;
	}

	public Date getDataCriacaoDe() {
		return dataCriacaoDe;
	}

	public void setDataCriacaoDe(Date dataCriacaoDe) {
		this.dataCriacaoDe = dataCriacaoDe;
	}

	public Date getDataCriacaoAte() {
		return dataCriacaoAte;
	}

	public void setDataCriacaoAte(Date dataCriacaoAte) {
		this.dataCriacaoAte = dataCriacaoAte;
	}

	public String getNomeParcela() {
		return nomeParcela;
	}

	public void setNomeParcela(String nomeParcela) {
		this.nomeParcela = nomeParcela;
	}

	public String getNomeCultivo() {
		return nomeCultivo;
	}

	public void setNomeCultivo(String nomeCultivo) {
		this.nomeCultivo = nomeCultivo;
	}

	public StatusTarea[] getStatuses() {
		return statuses;
	}

	public void setStatuses(StatusTarea[] statuses) {
		this.statuses = statuses;
	}

}