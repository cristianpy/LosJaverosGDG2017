package com.algaworks.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.algaworks.pedidovenda.model.Funcionario;
import com.algaworks.pedidovenda.repository.filter.FuncionarioFilter;
import com.algaworks.pedidovenda.service.NegocioException;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class Funcionarios implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Funcionario guardar(Funcionario funcionario) {
				
		return  manager.merge(funcionario);
				 
	}
	
	@Transactional
	public void remover(Funcionario funcionario) {
		try {
			funcionario = porId(funcionario.getId());
			manager.remove(funcionario);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Funcionario no puede ser excluído.");
		}
	}

	
	//@SuppressWarnings("unchecked")
	@SuppressWarnings("unchecked")
	public List<Funcionario> filtrados(FuncionarioFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Funcionario.class);
		
		if (StringUtils.isNotBlank(filtro.getId())) {
			criteria.add(Restrictions.eq("id", filtro.getId()));
		}
		
		if (StringUtils.isNotBlank(filtro.getNombre())) {
			criteria.add(Restrictions.ilike("nombre", filtro.getNombre(), MatchMode.ANYWHERE));
		}
		
		//return criteria.list();
		
		return criteria.addOrder(Order.asc("nombre")).list();
	}

	public Funcionario porId(Long id) {
		return manager.find(Funcionario.class, id);
	}
	
	public Funcionario porNome(String nombre) {
		try {
			return manager.createQuery("from Funcionario where upper(nombre) = :nombre", Funcionario.class)
				.setParameter("nombre", nombre.toUpperCase())
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Funcionario> funcionarios() {
		// TODO filtrar apenas func  (por um grupo específico)
		return this.manager.createQuery("from Funcionario", Funcionario.class)
				.getResultList();
	}

	public List<Funcionario> porNomeLista(String nombre) {

		return this.manager.createQuery("from Funcionario " +
				"where upper(nombre) like :nombre", Funcionario.class)
				.setParameter("nombre", nombre.toUpperCase() + "%")
				.getResultList();
		
	}
	
	
}
	
	


	

