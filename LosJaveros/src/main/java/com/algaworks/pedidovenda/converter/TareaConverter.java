package com.algaworks.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.algaworks.pedidovenda.model.Tarea;
import com.algaworks.pedidovenda.repository.Tareas;
import com.algaworks.pedidovenda.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Tarea.class)
public class TareaConverter implements Converter {

	//@Inject
	private Tareas tareas;
	
	public TareaConverter() {
		tareas = CDIServiceLocator.getBean(Tareas.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Tarea retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = tareas.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Tarea tarea = (Tarea) value;
			return tarea.getId() == null ? null : tarea.getId().toString();
		}
		
		return "";
	}

}
