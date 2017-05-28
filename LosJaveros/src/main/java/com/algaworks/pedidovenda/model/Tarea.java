package com.algaworks.pedidovenda.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tarea")
public class Tarea implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Date dataCriacao;
	private String observacao;
	private Date dataEntrega;
	
	private StatusTarea status= StatusTarea.EN_CURSO;
	private FormaPedido formaPedido;
	private Insumo insumo;

	
	private Cultivo cultivo;
	private Parcela parcela;
	private List<ItemTarea> itens = new ArrayList<>();

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tarea_seq")
	@SequenceGenerator(name = "tarea_seq", sequenceName = "tarea_seq", allocationSize=1)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_criacao", nullable = false)
	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	@Column(columnDefinition = "text")
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "data_entrega", nullable = true)
	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

		@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	public StatusTarea getStatus() {
		return status;
	}

	/*public void setStatus(StatusTarea status) {
		this.status = status;
	}*/

		public void setStatus(StatusTarea status) {
			this.status = status;
		}

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "forma_pedido", nullable = false, length = 20)
	public FormaPedido getFormaPedido() {
		return formaPedido;
	}

	public void setFormaPedido(FormaPedido formaPedido) {
		this.formaPedido = formaPedido;
	}
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "cultivo_id", nullable = false)
	public Cultivo getCultivo() {
		return cultivo;
	}

	public void setCultivo(Cultivo cultivo) {
		this.cultivo = cultivo;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "parcela_id", nullable = false)
	public Parcela getParcela() {
		return parcela;
	}

	public void setParcela(Parcela parcela) {
		this.parcela = parcela;
	}

	
	

	@OneToMany(mappedBy = "tarea", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<ItemTarea> getItens() {
		return itens;
	}

	public void setItens(List<ItemTarea> itens) {
		this.itens = itens;
	}
	

	@Transient
	public boolean isNovo() {
		return getId() == null;
	}
	
	@Transient
	public boolean isExistente() {
		return !isNovo();
	}
	
	

	public Insumo getInsumo() {
		return insumo;
	}

	public void setInsumo(Insumo insumo) {
		this.insumo = insumo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarea other = (Tarea) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	@Transient
	public boolean isEnCurso() {
		return StatusTarea.EN_CURSO.equals(this.getStatus());
	}
	
	public void removerItemVazio() {
		System.out.println("2221xxxx item remove...");

		ItemTarea primeiroItem = this.getItens().get(0);
		
		if (primeiroItem != null ) {//&& primeiroItem.getInsumo().getId() == null
			System.out.println("xxxx item remove...");
			this.getItens().remove(0);
		}
		
		
	}
	public void adicionarItemVazio() {
		if (this.isEnCurso()) {
			Insumo insumo = new Insumo();
			
			ItemTarea item = new ItemTarea();
			item.setInsumo(insumo);
			item.setTarea(this);			
			this.getItens().add(0, item);
		}
	}
	
	

	@Transient
	public boolean isNaoEmissivel() {
		return !this.isEmissivel();
	}

	@Transient
	public boolean isEmissivel() {
		return this.isExistente() && this.isEnCurso();
	}


	@Transient
	public boolean isCancelavel() {
		return this.isExistente() && !this.isCancelado();
	}

	@Transient
	private boolean isCancelado() {
		return StatusTarea.CANCELADO.equals(this.getStatus());
	}

	@Transient
	public boolean isNaoCancelavel() {
		return !this.isCancelavel();
	}

	@Transient
	public boolean isAlteravel() {
		return this.isEnCurso();
	}
	
	@Transient
	public boolean isNaoAlteravel() {
		return !this.isAlteravel();
	}

	
}