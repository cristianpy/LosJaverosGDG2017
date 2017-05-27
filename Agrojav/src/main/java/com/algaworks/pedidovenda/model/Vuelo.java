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
@Table(name = "vuelo")
public class Vuelo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Date dataCriacao;
	private String observacao;
	private Date dataEntrega;
	
	private StatusVuelo status= StatusVuelo.EN_CAMPO;
	private VantActuante vantActuante;
	private Archivo archivo;

	
	private Funcionario funcionario;
	private Solicitante solicitante;
	private List<ItemVueloArchivo> itens = new ArrayList<>();

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vuelo_seq")
	@SequenceGenerator(name = "vuelo_seq", sequenceName = "vuelo_seq", allocationSize=1)
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
	public StatusVuelo getStatus() {
		return status;
	}

	/*public void setStatus(StatusVuelo status) {
		this.status = status;
	}*/

		public void setStatus(StatusVuelo status) {
			this.status = status;
		}

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "vant_actuante", nullable = false, length = 20)
	public VantActuante getVantActuante() {
		return vantActuante;
	}

	public void setVantActuante(VantActuante vantActuante) {
		this.vantActuante = vantActuante;
	}
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "funcionario_id", nullable = false)
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "solicitante_id", nullable = false)
	public Solicitante getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Solicitante solicitante) {
		this.solicitante = solicitante;
	}

	
	

	@OneToMany(mappedBy = "vuelo", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<ItemVueloArchivo> getItens() {
		return itens;
	}

	public void setItens(List<ItemVueloArchivo> itens) {
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
	
	

	public Archivo getArchivo() {
		return archivo;
	}

	public void setArchivo(Archivo archivo) {
		this.archivo = archivo;
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
		Vuelo other = (Vuelo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	@Transient
	public boolean isEnCurso() {
		return StatusVuelo.EN_CAMPO.equals(this.getStatus());
	}
	
	public void removerItemVazio() {
		System.out.println("2221xxxx item remove...");

		ItemVueloArchivo primeiroItem = this.getItens().get(0);
		
		if (primeiroItem != null ) {//&& primeiroItem.getArchivo().getId() == null
			System.out.println("xxxx item remove...");
			this.getItens().remove(0);
		}
		
		
	}
	public void adicionarItemVazio() {
		if (this.isEnCurso()) {
			Archivo archivo = new Archivo();
			
			ItemVueloArchivo item= new ItemVueloArchivo();
			item.setArchivo(archivo);
			item.setVuelo(this);			
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
		return StatusVuelo.CONCLUIDO.equals(this.getStatus());
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