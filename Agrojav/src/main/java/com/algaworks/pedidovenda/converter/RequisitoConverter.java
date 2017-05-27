package com.algaworks.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.algaworks.pedidovenda.model.Requisito;
import com.algaworks.pedidovenda.repository.Requisitos;
import com.algaworks.pedidovenda.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Requisito.class)
public class RequisitoConverter implements Converter {

	//@Inject
	private Requisitos requisitos;
	
	public RequisitoConverter() {
		requisitos = CDIServiceLocator.getBean(Requisitos.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Requisito retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = requisitos.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Requisito requisito = (Requisito) value;
			return requisito.getId() == null ? null : requisito.getId().toString();
		}
		
		return "";
	}

}
