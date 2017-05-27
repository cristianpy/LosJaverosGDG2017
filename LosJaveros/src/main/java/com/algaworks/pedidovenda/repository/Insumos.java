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

import com.algaworks.pedidovenda.model.Insumo;
import com.algaworks.pedidovenda.repository.filter.InsumoFilter;
import com.algaworks.pedidovenda.service.NegocioException;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class Insumos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Insumo guardar(Insumo insumo) {
		return manager.merge(insumo);
	}
	
	@Transactional
	public void remover(Insumo insumo) {
		try {
			insumo = porId(insumo.getId());
			manager.remove(insumo);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Insumo no puede ser excluído.");
		}
	}

	public Insumo porSku(String sku) {
		try {
			return manager.createQuery("from Insumo where upper(sku) = :sku", Insumo.class)
				.setParameter("sku", sku.toUpperCase())
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Insumo> filtrados(InsumoFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Insumo.class);
		
		
		
		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
	}

	public Insumo porId(Long id) {
		return manager.find(Insumo.class, id);
	}

	public List<Insumo> porNome(String nome) {
		return this.manager.createQuery("from Insumo where upper(nome) like :nome", Insumo.class)
				.setParameter("nome", nome.toUpperCase() + "%").getResultList();
	}
	
	public Insumo porunNome(String nome) {
		try {
			return manager.createQuery("from Insumo where upper(nome) = :nome", Insumo.class)
				.setParameter("nome", nome.toUpperCase())
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	

}