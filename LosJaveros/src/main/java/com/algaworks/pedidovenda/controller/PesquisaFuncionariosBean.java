package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Funcionario;
import com.algaworks.pedidovenda.repository.Funcionarios;
import com.algaworks.pedidovenda.repository.filter.FuncionarioFilter;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaFuncionariosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Funcionarios funcionarios;
	
	private FuncionarioFilter filtro;
	private List<Funcionario> funcionariosFiltrados;
	
	private Funcionario funcionarioSelecionado;
	
	public PesquisaFuncionariosBean() {
		filtro = new FuncionarioFilter();
	}
	
	public void excluir() {
		funcionarios.remover(funcionarioSelecionado);
		funcionariosFiltrados.remove(funcionarioSelecionado);
		
		FacesUtil.addInfoMessage("Funcionario " + funcionarioSelecionado.getId() 
				+ " excluído com suceso.");
	}
	
	public void pesquisar() {
		funcionariosFiltrados = funcionarios.filtrados(filtro);
	}
	
	public List<Funcionario> getFuncionariosFiltrados() {
		return funcionariosFiltrados;
	}

	public FuncionarioFilter getFiltro() {
		return filtro;
	}

	public Funcionario getFuncionarioSelecionado() {
		return funcionarioSelecionado;
	}

	public void setFuncionarioSelecionado(Funcionario funcionarioSelecionado) {
		this.funcionarioSelecionado = funcionarioSelecionado;
	}
	
}