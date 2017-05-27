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

import com.algaworks.pedidovenda.model.Solicitante;
import com.algaworks.pedidovenda.repository.filter.SolicitanteFilter;
import com.algaworks.pedidovenda.service.NegocioException;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class Solicitantes implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Solicitante guardar(Solicitante solicitante) {

		return manager.merge(solicitante);

	}

	@Transactional
	public void remover(Solicitante solicitante) {
		try {
			solicitante = porId(solicitante.getId());
			manager.remove(solicitante);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Solicitante no puede ser excluído.");
		}
	}

	// @SuppressWarnings("unchecked")
	@SuppressWarnings("unchecked")
	public List<Solicitante> filtrados(SolicitanteFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Solicitante.class);

		if (StringUtils.isNotBlank(filtro.getId())) {
			criteria.add(Restrictions.eq("id", filtro.getId()));
		}

		if (StringUtils.isNotBlank(filtro.getNombre())) {
			criteria.add(Restrictions.ilike("nombre", filtro.getNombre(),
					MatchMode.ANYWHERE));
		}

		// return criteria.list();

		return criteria.addOrder(Order.asc("nombre")).list();
	}

	public Solicitante porId(Long id) {
		return manager.find(Solicitante.class, id);
	}
	
	
	public List<Solicitante> porNomeLista(String nombre) {
		return this.manager.createQuery("from Solicitante " +
				"where upper(nombre) like :nombre", Solicitante.class)
				.setParameter("nombre", nombre.toUpperCase() + "%")
				.getResultList();
	}
	
		public Solicitante porNome(String nombre) {
		try {
			return manager
					.createQuery(
							"from Solicitante where upper(nombre) = :nombre",
							Solicitante.class)
					.setParameter("nombre", nombre.toUpperCase())
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
