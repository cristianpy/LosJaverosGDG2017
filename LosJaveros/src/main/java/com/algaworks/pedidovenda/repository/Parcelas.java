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

import com.algaworks.pedidovenda.model.Parcela;
import com.algaworks.pedidovenda.repository.filter.ParcelaFilter;
import com.algaworks.pedidovenda.service.NegocioException;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class Parcelas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Parcela guardar(Parcela parcela) {
		return manager.merge(parcela);
	}
	
	@Transactional
	public void remover(Parcela parcela) {
		try {
			parcela = porId(parcela.getId());
			manager.remove(parcela);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Parcela no puede ser excluído.");
		}
	}

	public Parcela porSku(String sku) {
		try {
			return manager.createQuery("from Parcela where upper(sku) = :sku", Parcela.class)
				.setParameter("sku", sku.toUpperCase())
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Parcela> filtrados(ParcelaFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Parcela.class);
		
		
		
		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
	}

	public Parcela porId(Long id) {
		return manager.find(Parcela.class, id);
	}

	public List<Parcela> porNome(String nome) {
		return this.manager.createQuery("from Parcela where upper(nome) like :nome", Parcela.class)
				.setParameter("nome", nome.toUpperCase() + "%").getResultList();
	}
	
	public Parcela porunNome(String nome) {
		try {
			return manager.createQuery("from Parcela where upper(nome) = :nome", Parcela.class)
				.setParameter("nome", nome.toUpperCase())
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Parcela> porNomeLista(String nome) {

		return this.manager.createQuery("from Parcela " +
				"where upper(nome) like :nome", Parcela.class)
				.setParameter("nome", nome.toUpperCase() + "%")
				.getResultList();
		
	}

}