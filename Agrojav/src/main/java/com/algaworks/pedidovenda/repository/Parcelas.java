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
				
		return  manager.merge(parcela);
				 
	}
	
	@Transactional
	public void remover(Parcela parcela) {
		try {
			parcela = porId(parcela.getId());
			manager.remove(parcela);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Parcela no puede ser excluída.");
		}
	}

	
	//@SuppressWarnings("unchecked")
	@SuppressWarnings("unchecked")
	public List<Parcela> filtrados(ParcelaFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Parcela.class);
		
		if (StringUtils.isNotBlank(filtro.getId())) {
			criteria.add(Restrictions.eq("id", filtro.getId()));
		}
		
		if (StringUtils.isNotBlank(filtro.getNombre())) {
			criteria.add(Restrictions.ilike("descripcion", filtro.getNombre(), MatchMode.ANYWHERE));
		}
		
		//return criteria.list();
		
		return criteria.addOrder(Order.asc("descripcion")).list();
	}

	public Parcela porId(Long id) {
		return manager.find(Parcela.class, id);
	}
	
	public Parcela porNombre(String descripcion) {
		try {
			return manager.createQuery("from Parcela where upper(descripcion) = :descripcion", Parcela.class)
				.setParameter("descripcion", descripcion.toUpperCase())
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Parcela> parcelas() {
		// TODO filtrar apenas func  (por um grupo específico)
		return this.manager.createQuery("from Parcela", Parcela.class)
				.getResultList();
	}

	public List<Parcela> porNombreLista(String descripcion) {

		return this.manager.createQuery("from Parcela " +
				"where upper(descripcion) like :descripcion", Parcela.class)
				.setParameter("descripcion", descripcion.toUpperCase() + "%")
				.getResultList();
		
	}
	
	
}
	
	


	

