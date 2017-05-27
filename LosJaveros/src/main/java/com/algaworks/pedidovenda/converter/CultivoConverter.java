package com.algaworks.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.algaworks.pedidovenda.model.Cultivo;
import com.algaworks.pedidovenda.repository.Cultivos;
import com.algaworks.pedidovenda.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Cultivo.class)
public class CultivoConverter implements Converter {

	//@Inject
	private Cultivos cultivos;
	
	public CultivoConverter() {
		cultivos = CDIServiceLocator.getBean(Cultivos.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Cultivo retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = cultivos.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Cultivo insumo = (Cultivo) value;
			return insumo.getId() == null ? null : insumo.getId().toString();
		}
		
		return "";
	}

}
