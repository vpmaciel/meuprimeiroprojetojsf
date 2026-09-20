package br.com.converter;

import br.com.entidades.Framework;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter(forClass = Framework.class)
public class FrameworkConverter implements Converter<Framework> {

    @Override
    public Framework getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        Framework f = new Framework();
        f.setId(Long.parseLong(value));
        return f;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Framework value) {
        if (value == null || value.getId() == null) {
            return "";
        }
        return value.getId().toString();
    }
}