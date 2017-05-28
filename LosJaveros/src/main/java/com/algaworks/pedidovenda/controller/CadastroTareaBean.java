
package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.FormaPedido;
import com.algaworks.pedidovenda.model.Cultivo;
import com.algaworks.pedidovenda.model.ItemTarea;
import com.algaworks.pedidovenda.model.Insumo;
import com.algaworks.pedidovenda.model.Parcela;
import com.algaworks.pedidovenda.model.StatusTarea;
import com.algaworks.pedidovenda.model.Tarea;
import com.algaworks.pedidovenda.repository.Cultivos;
import com.algaworks.pedidovenda.repository.Insumos;
import com.algaworks.pedidovenda.repository.Parcelas;
import com.algaworks.pedidovenda.service.CadastroTareaService;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroTareaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	
	
	@Inject
	private Parcelas parcelas;
	@Inject
	private Cultivos cultivos;
	@Inject
	private CadastroTareaService cadastroTareaService;
	@Inject
	private Insumos insumos;
	
	private Long id;
	
	@Produces
	@TareaEdicao
	private Tarea tarea;
	
	private List<Cultivo> cultivo;
	
	private Insumo insumoLinhaEditavel;

	
	private List<Parcela> parcela;
	

	
	
	public CadastroTareaBean() {
		limpar();
	}

	public void tareaAlterado(@Observes TareaAlteradoEvent event) {
		this.tarea = event.getTarea();
	}
	
	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			
			this.tarea.adicionarItemVazio();
			
		/*	this.cultivos = this.fucionarios.cultivos();*/
		}
	}
	
	private void limpar() {
		tarea = new Tarea();
		
	}
	
	public void salvar() {
       this.tarea.removerItemVazio();
		
		try {
		this.tarea = this.cadastroTareaService.salvar(this.tarea);
		
		FacesUtil.addInfoMessage("Movimiento  Guardado!");
	}finally {
		this.tarea.adicionarItemVazio();
	}
	}
	
	public void carregarInsumoPorId() {
		 System.out.println("este 1" + this.id );
		if (this.id!=null)
		{
		  System.out.println("este 2" + this.id );
			this.insumoLinhaEditavel = this.insumos.porId(this.id);
			this.carregarInsumoLinhaEditavel();
		}
	}
	
	
	
	public void carregarInsumoLinhaEditavel() {
		ItemTarea item = this.tarea.getItens().get(0);
		
		if (this.insumoLinhaEditavel != null) {
			if (this.existeItemComInsumo(this.insumoLinhaEditavel)) {
				FacesUtil.addErrorMessage("Ya existe un insumo informado.");
			} else {
				item.setInsumo(this.insumoLinhaEditavel);
				
				this.tarea.adicionarItemVazio();
				this.insumoLinhaEditavel= null;
				this.id = null;
				
			}
		}
	}
	
	private boolean existeItemComInsumo(Insumo  insumo) {
boolean existeItem = false;
		
		for (ItemTarea item : this.getTarea().getItens()) {
			if (insumo.equals(item.getInsumo())) {
				existeItem = true;
				break;
			}
		} 
		
		return existeItem;
	}
	
	public List<Insumo> completarInsumo(String nome) {
		return this.insumos.porNome(nome);
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
	
	public List<Parcela> completarParcela(String nombre) {
		return this.parcelas.porNomeLista(nombre);
	}
	

	public List<Cultivo> completarCultivo(String nome) {
		return this.cultivos.porNomeLista(nome);
	}
	
	public Tarea getTarea() {
		return tarea;
	}
	
	

	

	public List<Parcela> getParcela() {
		return parcela;
	}

	public void setParcela(List<Parcela> parcela) {
		this.parcela = parcela;
	}

	public List<Cultivo> getCultivo() {
		return cultivo;
	}

	public void setCultivo(List<Cultivo> cultivo) {
		this.cultivo = cultivo;
	}

	public Cultivos getCultivos() {
		return cultivos;
	}

	public void setCultivos(Cultivos cultivos) {
		this.cultivos = cultivos;
	}

	public Insumo getInsumoLinhaEditavel() {
		return insumoLinhaEditavel;
	}

	public void setInsumoLinhaEditavel(Insumo insumoLinhaEditavel) {
		this.insumoLinhaEditavel = insumoLinhaEditavel;
	}

	

	public boolean isEditando() {
		return this.tarea.getId() != null;
	}

	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}
	

	

	
	

}