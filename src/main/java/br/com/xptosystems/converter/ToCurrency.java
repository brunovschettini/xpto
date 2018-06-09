package br.com.xptosystems.converter;

import br.com.xptosystems.utils.Currency;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class ToCurrency implements Converter {

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return (String) value; // Or (value != null) ? value.toString().toUpperCase() : null;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null) {
            if (value.isEmpty()) {
                value = "0";
            }
            String somenteLetras = value.replaceAll("[^a-zA-Z]", "");
            if (somenteLetras.length() > 0) {
                return null;
            }
            value = value.replace("-", "");
            if (value.isEmpty()) {
                return null;
            }
            return Currency.conveterDolarToReal(value);
        }
        return null;
    }

}
