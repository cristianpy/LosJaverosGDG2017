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

import com.algaworks.pedidovenda.model.Archivo;
import com.algaworks.pedidovenda.repository.filter.ArchivoFilter;
import com.algaworks.pedidovenda.service.NegocioException;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class Archivos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Archivo guardar(Archivo Archivo) {
		return manager.merge(Archivo);
	}
	
	@Transactional
	public void remover(Archivo Archivo) {
		try {
			Archivo = porId(Archivo.getId());
			manager.remove(Archivo);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Archivo não pode ser excluído.");
		}
	}

	public Archivo porSku(String sku) {
		try {
			return manager.createQuery("from Archivo where upper(sku) = :sku", Archivo.class)
				.setParameter("sku", sku.toUpperCase())
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Archivo> filtrados(ArchivoFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Archivo.class);
		
		
		
		if (StringUtils.isNotBlank(filtro.getruta())) {
			criteria.add(Restrictions.ilike("ruta", filtro.getruta(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("ruta")).list();
	}

	public Archivo porId(Long id) {
		return manager.find(Archivo.class, id);
	}

	public List<Archivo> porRuta(String ruta) {
		return this.manager.createQuery("from Archivo where upper(ruta) like :ruta", Archivo.class)
				.setParameter("ruta", ruta.toUpperCase() + "%").getResultList();
	}
	
	public Archivo porunRuta(String ruta) {
		try {
			return manager.createQuery("from Archivo where upper(ruta) = :ruta", Archivo.class)
				.setParameter("ruta", ruta.toUpperCase())
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	

}