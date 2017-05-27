package com.algaworks.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.algaworks.pedidovenda.model.Solicitante;
import com.algaworks.pedidovenda.repository.Solicitantes;
import com.algaworks.pedidovenda.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Solicitante.class)
public class SolicitanteConverter implements Converter {

	//@Inject
	private Solicitantes solicitantes;
	
	public SolicitanteConverter() {
		solicitantes = CDIServiceLocator.getBean(Solicitantes.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Solicitante retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = solicitantes.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Solicitante solicitante = (Solicitante) value;
			return solicitante.getId() == null ? null : solicitante.getId().toString();
		}
		
		return "";
	}

}
