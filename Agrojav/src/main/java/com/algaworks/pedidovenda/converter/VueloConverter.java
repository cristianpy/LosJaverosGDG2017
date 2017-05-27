package com.algaworks.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.algaworks.pedidovenda.model.Vuelo;
import com.algaworks.pedidovenda.repository.Vuelos;
import com.algaworks.pedidovenda.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Vuelo.class)
public class VueloConverter implements Converter {

	//@Inject
	private Vuelos vuelos;
	
	public VueloConverter() {
		vuelos = CDIServiceLocator.getBean(Vuelos.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Vuelo retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = vuelos.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Vuelo vuelo = (Vuelo) value;
			return vuelo.getId() == null ? null : vuelo.getId().toString();
		}
		
		return "";
	}

}
