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

import com.algaworks.pedidovenda.model.TipoCultivo;
import com.algaworks.pedidovenda.repository.filter.TipoCultivoFilter;
import com.algaworks.pedidovenda.service.NegocioException;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class TiposCultivos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public TipoCultivo guardar(TipoCultivo tipoCultivo) {
				
		return  manager.merge(tipoCultivo);
				 
	}
	
	@Transactional
	public void remover(TipoCultivo tipoCultivo) {
		try {
			tipoCultivo = porId(tipoCultivo.getId());
			manager.remove(tipoCultivo);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("TipoCultivo no puede ser excluído.");
		}
	}

	
	//@SuppressWarnings("unchecked")
	@SuppressWarnings("unchecked")
	public List<TipoCultivo> filtrados(TipoCultivoFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(TipoCultivo.class);
		
		if (StringUtils.isNotBlank(filtro.getId())) {
			criteria.add(Restrictions.eq("id", filtro.getId()));
		}
		
		if (StringUtils.isNotBlank(filtro.getDescripcion())) {
			criteria.add(Restrictions.ilike("descripcion", filtro.getDescripcion(), MatchMode.ANYWHERE));
		}
		
		//return criteria.list();
		
		return criteria.addOrder(Order.asc("descripcion")).list();
	}

	public TipoCultivo porId(Long id) {
		return manager.find(TipoCultivo.class, id);
	}
	
	public TipoCultivo porDescripcion(String descripcion) {
		try {
			return manager.createQuery("from tipo_cultivo where upper(descripcion) = :descripcion", TipoCultivo.class)
				.setParameter("descripcion", descripcion.toUpperCase())
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<TipoCultivo> tipoCultivos() {
		return this.manager.createQuery("from TipoCultivo", TipoCultivo.class)
				.getResultList();
	}

	public List<TipoCultivo> porNomeLista(String descripcion) {

		return this.manager.createQuery("from tipo_cultivo " +
				"where upper(descripcion) like :descripcion", TipoCultivo.class)
				.setParameter("descripcion", descripcion.toUpperCase() + "%")
				.getResultList();
		
	}
	
	
}
	
	


	

