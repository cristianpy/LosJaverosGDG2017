 package com.algaworks.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.pedidovenda.model.Funcionario;
import com.algaworks.pedidovenda.repository.Funcionarios;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class CadastroFuncionarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Funcionarios funcionarios;
	
	@Transactional
	public Funcionario salvar(Funcionario funcionario) {
		Funcionario funcionarioExistente = funcionarios.porNome(funcionario.getNombre());
		
	if (funcionarioExistente != null && !funcionarioExistente.equals(funcionario)){
			throw new NegocioException("Ya existe um funcionario con nombre informado.");
		}
		
		
		return funcionarios.guardar(funcionario);
	}

	
}
