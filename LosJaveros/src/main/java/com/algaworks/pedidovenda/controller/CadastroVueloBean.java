
package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Archivo;
import com.algaworks.pedidovenda.model.FormaPedido;
import com.algaworks.pedidovenda.model.Funcionario;
import com.algaworks.pedidovenda.model.ItemVueloArchivo;
import com.algaworks.pedidovenda.model.Solicitante;
import com.algaworks.pedidovenda.model.StatusVuelo;
import com.algaworks.pedidovenda.model.VantActuante;
import com.algaworks.pedidovenda.model.Vuelo;
import com.algaworks.pedidovenda.repository.Archivos;
import com.algaworks.pedidovenda.repository.Funcionarios;
import com.algaworks.pedidovenda.repository.Solicitantes;
import com.algaworks.pedidovenda.service.CadastroVueloService;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroVueloBean implements Serializable {

	private static final long serialVersionUID = 1L;

	
	
	@Inject
	private Solicitantes solicitantes;
	@Inject
	private Funcionarios funcionarios;
	@Inject
	private CadastroVueloService cadastroVueloService;
	@Inject
	private Archivos archivos;
	
	private Long id;
	
	@Produces
	@VueloEdicao
	private Vuelo vuelo;
	
	private List<Funcionario> funcionario;
	
	private Archivo archivoLinhaEditavel;

	
	private List<Solicitante> solicitante;
	

	
	
	public CadastroVueloBean() {
		limpar();
	}

	public void vueloAlterado(@Observes VueloAlteradoEvent event) {
		this.vuelo = event.getVuelo();
	}
	
	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			
			this.vuelo.adicionarItemVazio();
			
		/*	this.funcionarios = this.fucionarios.funcionarios();*/
		}
	}
	
	private void limpar() {
		vuelo = new Vuelo();
		
	}
	
	public void salvar() {
       this.vuelo.removerItemVazio();
		
		try {
		this.vuelo = this.cadastroVueloService.salvar(this.vuelo);
		
		FacesUtil.addInfoMessage("Vuelo Guardado!");
	}finally {
		this.vuelo.adicionarItemVazio();
	}
	}
	
public void carregarArchivoPorId() {
		 System.out.println("este 1" + this.id );
		if (this.id!=null)
		{
		  System.out.println("este 2" + this.id );
			this.archivoLinhaEditavel = this.archivos.porId(this.id);
			this.carregarArchivoLinhaEditavel();
		}
	}
	
	
	
public void carregarArchivoLinhaEditavel() {
		ItemVueloArchivo item = this.vuelo.getItens().get(0);
		
		if (this.archivoLinhaEditavel != null) {
			if (this.existeItemComArchivo(this.archivoLinhaEditavel)) {
				FacesUtil.addErrorMessage("Ya existe un archivo informado.");
			} else {
				item.setArchivo(this.archivoLinhaEditavel);
				
				this.vuelo.adicionarItemVazio();
				this.archivoLinhaEditavel= null;
				this.id = null;
				
			}
		}
	}
	
	private boolean existeItemComArchivo(Archivo  archivo) {
boolean existeItem = false;
		
		for (ItemVueloArchivo item : this.getVuelo().getItens()) {
			if (archivo.equals(item.getArchivo())) {
				existeItem = true;
				break;
			}
		} 
		
		return existeItem;
	}
	
	public List<Archivo> completarArchivo(String ruta) {
		return this.archivos.porRuta(ruta);
	}
	
	
	public void removerItem(ItemVueloArchivo item, int linha) {
		
				this.getVuelo().getItens().remove(linha);
					
	}
	
	
	
		
	public FormaPedido[] getFormasPedido() {
		return FormaPedido.values();
	}
	
	public StatusVuelo[] getStatusVuelo() {
		return StatusVuelo.values();
	}
	public VantActuante[] getVantActuante(){
		return VantActuante.values();
	}
	
	public List<Solicitante> completarSolicitante(String nombre) {
		return this.solicitantes.porNomeLista(nombre);
	}
	

	public List<Funcionario> completarFuncionario(String nombre) {
		return this.funcionarios.porNomeLista(nombre);
	}
	
	public Vuelo getVuelo() {
		return vuelo;
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

	public Archivo getArchivoLinhaEditavel() {
		return archivoLinhaEditavel;
	}

	public void setArchivoLinhaEditavel(Archivo archivoLinhaEditavel) {
		this.archivoLinhaEditavel = archivoLinhaEditavel;
	}

	

	public boolean isEditando() {
		return this.vuelo.getId() != null;
	}

	public void setVuelo(Vuelo vuelo) {
		this.vuelo = vuelo;
	}
	

	

	
	

}