	package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import com.algaworks.pedidovenda.model.Categoria;
import com.algaworks.pedidovenda.model.Funcionario;
import com.algaworks.pedidovenda.repository.Categorias;
import com.algaworks.pedidovenda.service.CadastroFuncionarioService;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroFuncionarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Categorias categorias;

	private Categoria categoriaPai;

	@Inject
	private CadastroFuncionarioService cadastroFuncionarioService;

	private Funcionario funcionario;
	private List<Categoria> categoriasRaizes;
	private List<Categoria> subcategorias;

	public CadastroFuncionarioBean() {
		limpar();
	}

	public void inicializar() {
		/* System.out.println("Inicializando..."); */

		if (FacesUtil.isNotPostback()) {
			categoriasRaizes = categorias.raizes();
			
			if (this.categoriaPai != null) {
				carregarSubcategorias();
			}
		}
	}

	public void carregarSubcategorias() {
		subcategorias = categorias.subcategoriasDe(categoriaPai);
	}

	private void limpar() {
		funcionario = new Funcionario();
		categoriaPai = null;
		subcategorias = new ArrayList<>();
	}

	public void salvar() {
		this.funcionario = (Funcionario) cadastroFuncionarioService.salvar(this.funcionario);
		limpar();

		FacesUtil.addInfoMessage("Funcionario se agrego corectamente!");
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionarioe(Funcionario funcionario) {
		this.funcionario = funcionario;
		if (this.funcionario != null) {
			this.categoriaPai = this.funcionario.getCategoria().getCategoriaPai();
		}
	}

	public List<Categoria> getCategoriasRaizes() {
		return categoriasRaizes;
	}

	public Categorias getCategorias() {
		return categorias;
	}

	@NotNull
	public Categoria getCategoriaPai() {
		return categoriaPai; 
	}

	public void setCategoriaPai(Categoria categoriaPai) {
		this.categoriaPai = categoriaPai;
	}

	public List<Categoria> getSubcategorias() {
		return subcategorias;
	}
	
	public boolean isEditando() {
		return this.funcionario.getId() != null;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
		if (this.funcionario != null) {
			this.categoriaPai = this.funcionario.getCategoria().getCategoriaPai();
		}
	}

}