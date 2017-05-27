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

import com.algaworks.pedidovenda.model.Cultivo;
import com.algaworks.pedidovenda.repository.filter.CultivoFilter;
import com.algaworks.pedidovenda.service.NegocioException;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class Cultivos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Cultivo guardar(Cultivo insumo) {
		return manager.merge(insumo);
	}
	
	@Transactional
	public void remover(Cultivo insumo) {
		try {
			insumo = porId(insumo.getId());
			manager.remove(insumo);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Cultivo no puede ser excluído.");
		}
	}

	public Cultivo porSku(String sku) {
		try {
			return manager.createQuery("from Cultivo where upper(sku) = :sku", Cultivo.class)
				.setParameter("sku", sku.toUpperCase())
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Cultivo> filtrados(CultivoFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Cultivo.class);
		
		
		
		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
	}

	public Cultivo porId(Long id) {
		return manager.find(Cultivo.class, id);
	}

	public List<Cultivo> porNome(String nome) {
		return this.manager.createQuery("from Cultivo where upper(nome) like :nome", Cultivo.class)
				.setParameter("nome", nome.toUpperCase() + "%").getResultList();
	}
	
	public Cultivo porunNome(String nome) {
		try {
			return manager.createQuery("from Cultivo where upper(nome) = :nome", Cultivo.class)
				.setParameter("nome", nome.toUpperCase())
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	

}