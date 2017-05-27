package com.algaworks.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.algaworks.pedidovenda.model.Parcela;
import com.algaworks.pedidovenda.repository.Parcelas;
import com.algaworks.pedidovenda.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Parcela.class)
public class ParcelaConverter implements Converter {

	//@Inject
	private Parcelas parcelas;
	
	public ParcelaConverter() {
		parcelas = CDIServiceLocator.getBean(Parcelas.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Parcela retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = parcelas.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Parcela parcela = (Parcela) value;
			return parcela.getId() == null ? null : parcela.getId().toString();
		}
		
		return "";
	}

}
