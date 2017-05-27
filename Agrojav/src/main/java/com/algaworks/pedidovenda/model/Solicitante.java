package com.algaworks.pedidovenda.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="solicitante")
public class Solicitante implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nombre;
	private Categoria categoria;
	private String telefono;
	private String correo;
	private String direccion;

	
	private String obs;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "solicitante_seq")
	@SequenceGenerator(name = "solicitante_seq", sequenceName = "solicitante_seq", allocationSize=1)	
		public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@NotBlank
	@Size(max = 80)
	@Column(nullable = false, length = 80)	
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	//@NotNull
	//@ManyToOne
    //@JoinColumn(name = "categoria_id", nullable = false)
	@Column(nullable = false, length = 80)
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	@NotBlank
	@Size(max = 80)
	@Column(nullable = false, length = 80)
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	
	@Email
	@Size(max = 80)
	@Column(nullable = false, length = 80)
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	@NotBlank
	@Size(max = 80)
	@Column(nullable = false, length = 80)
	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}
	
	
	
	@NotBlank
	@Size(max = 80)
	@Column(nullable = false, length = 80)
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Transient
	public boolean isNovo() {
		return getId() == null;
	}
	
	@Transient
	public boolean isExistente() {
		return !isNovo();
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
		Solicitante other = (Solicitante) obj;
		
		if (id == null)
		{ if (other.id != null)
				return false;
		} 
		else 
			if (!id.equals(other.id))
			return false;
		
		
		return true;
	}

	
	
	
	
}
