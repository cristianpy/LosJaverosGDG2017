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

import com.algaworks.pedidovenda.model.Requisito;
import com.algaworks.pedidovenda.repository.filter.RequisitoFilter;
import com.algaworks.pedidovenda.service.NegocioException;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class Requisitos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Requisito guardar(Requisito requisito) {
		return manager.merge(requisito);
	}
	
	@Transactional
	public void remover(Requisito requisito) {
		try {
			requisito = porId(requisito.getId());
			manager.remove(requisito);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Requisito no puede ser excluído.");
		}
	}

	public Requisito porSku(String sku) {
		try {
			return manager.createQuery("from Requisito where upper(sku) = :sku", Requisito.class)
				.setParameter("sku", sku.toUpperCase())
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Requisito> filtrados(RequisitoFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Requisito.class);
		
		
		
		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
	}

	public Requisito porId(Long id) {
		return manager.find(Requisito.class, id);
	}

	public List<Requisito> porNome(String nome) {
		return this.manager.createQuery("from Requisito where upper(nome) like :nome", Requisito.class)
				.setParameter("nome", nome.toUpperCase() + "%").getResultList();
	}
	
	public Requisito porunNome(String nome) {
		try {
			return manager.createQuery("from Requisito where upper(nome) = :nome", Requisito.class)
				.setParameter("nome", nome.toUpperCase())
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	

}