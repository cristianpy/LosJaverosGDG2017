package com.algaworks.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.algaworks.pedidovenda.model.TipoCultivo;
import com.algaworks.pedidovenda.repository.TiposCultivos;
import com.algaworks.pedidovenda.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = TipoCultivo.class)
public class TipoCultivoConverter implements Converter {

	//@Inject
	private TiposCultivos tipoCultivo;
	
	public TipoCultivoConverter() {
		tipoCultivo = CDIServiceLocator.getBean(TiposCultivos.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		TipoCultivo retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = tipoCultivo.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			TipoCultivo funcionario = (TipoCultivo) value;
			return funcionario.getId() == null ? null : funcionario.getId().toString();
		}
		
		return "";
	}

}
