package com.algaworks.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.algaworks.pedidovenda.model.Insumo;
import com.algaworks.pedidovenda.repository.Insumos;
import com.algaworks.pedidovenda.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Insumo.class)
public class InsumoConverter implements Converter {

	//@Inject
	private Insumos insumos;
	
	public InsumoConverter() {
		insumos = CDIServiceLocator.getBean(Insumos.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Insumo retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = insumos.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Insumo insumo = (Insumo) value;
			return insumo.getId() == null ? null : insumo.getId().toString();
		}
		
		return "";
	}

}
