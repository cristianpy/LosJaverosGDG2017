
package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.FormaPedido;
import com.algaworks.pedidovenda.model.Funcionario;
import com.algaworks.pedidovenda.model.ItemTarea;
import com.algaworks.pedidovenda.model.Requisito;
import com.algaworks.pedidovenda.model.Solicitante;
import com.algaworks.pedidovenda.model.StatusTarea;
import com.algaworks.pedidovenda.model.Tarea;
import com.algaworks.pedidovenda.repository.Funcionarios;
import com.algaworks.pedidovenda.repository.Requisitos;
import com.algaworks.pedidovenda.repository.Solicitantes;
import com.algaworks.pedidovenda.service.CadastroTareaService;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroTareaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	
	
	@Inject
	private Solicitantes solicitantes;
	@Inject
	private Funcionarios funcionarios;
	@Inject
	private CadastroTareaService cadastroTareaService;
	@Inject
	private Requisitos requisitos;
	
	private Long id;
	
	@Produces
	@TareaEdicao
	private Tarea tarea;
	
	private List<Funcionario> funcionario;
	
	private Requisito requisitoLinhaEditavel;

	
	private List<Solicitante> solicitante;
	

	
	
	public CadastroTareaBean() {
		limpar();
	}

	public void tareaAlterado(@Observes TareaAlteradoEvent event) {
		this.tarea = event.getTarea();
	}
	
	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			
			this.tarea.adicionarItemVazio();
			
		/*	this.funcionarios = this.fucionarios.funcionarios();*/
		}
	}
	
	private void limpar() {
		tarea = new Tarea();
		
	}
	
	public void salvar() {
       this.tarea.removerItemVazio();
		
		try {
		this.tarea = this.cadastroTareaService.salvar(this.tarea);
		
		FacesUtil.addInfoMessage("Tarea Guardada!");
	}finally {
		this.tarea.adicionarItemVazio();
	}
	}
	
	public void carregarRequisitoPorId() {
		 System.out.println("este 1" + this.id );
		if (this.id!=null)
		{
		  System.out.println("este 2" + this.id );
			this.requisitoLinhaEditavel = this.requisitos.porId(this.id);
			this.carregarRequisitoLinhaEditavel();
		}
	}
	
	
	
	public void carregarRequisitoLinhaEditavel() {
		ItemTarea item = this.tarea.getItens().get(0);
		
		if (this.requisitoLinhaEditavel != null) {
			if (this.existeItemComRequisito(this.requisitoLinhaEditavel)) {
				FacesUtil.addErrorMessage("Ya existe un requisito informado.");
			} else {
				item.setRequisito(this.requisitoLinhaEditavel);
				
				this.tarea.adicionarItemVazio();
				this.requisitoLinhaEditavel= null;
				this.id = null;
				
			}
		}
	}
	
	private boolean existeItemComRequisito(Requisito  requisito) {
boolean existeItem = false;
		
		for (ItemTarea item : this.getTarea().getItens()) {
			if (requisito.equals(item.getRequisito())) {
				existeItem = true;
				break;
			}
		} 
		
		return existeItem;
	}
	
	public List<Requisito> completarRequisito(String nome) {
		return this.requisitos.porNome(nome);
	}
	
	
	public void removerItem(ItemTarea item, int linha) {
		
				this.getTarea().getItens().remove(linha);
					
	}
	
	
	
		
	public FormaPedido[] getFormasPedido() {
		return FormaPedido.values();
	}
	
	public StatusTarea[] getStatusTarea() {
		return StatusTarea.values();
	}
	
	public List<Solicitante> completarSolicitante(String nombre) {
		return this.solicitantes.porNomeLista(nombre);
	}
	

	public List<Funcionario> completarFuncionario(String nombre) {
		return this.funcionarios.porNomeLista(nombre);
	}
	
	public Tarea getTarea() {
		return tarea;
	}
	
	

	

	public List<Solicitante> getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(List<Solicitante> solicitante) {
		this.solicitante = solicitante;
	}

	public List<Funcionario> getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(List<Funcionario> funcionario) {
		this.funcionario = funcionario;
	}

	public Funcionarios getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(Funcionarios funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Requisito getRequisitoLinhaEditavel() {
		return requisitoLinhaEditavel;
	}

	public void setRequisitoLinhaEditavel(Requisito requisitoLinhaEditavel) {
		this.requisitoLinhaEditavel = requisitoLinhaEditavel;
	}

	

	public boolean isEditando() {
		return this.tarea.getId() != null;
	}

	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}
	

	

	
	

}