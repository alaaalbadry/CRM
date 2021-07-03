package com.techvalueinsight.jsf.primefaces.view.helpers;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItem;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import com.techvalueinsight.jsf.primefaces.model.entities.City;
import com.techvalueinsight.jsf.primefaces.model.entities.Country;

@FacesConverter(value = "genericConverter")
public class GenericConverter implements Converter {

	@SuppressWarnings("unchecked")
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Object ret = null;
		UIComponent src = arg1;
		if (src != null && arg2 != null) {
			List<UIComponent> childs = src.getChildren();
			UISelectItems items = null;
			if (childs != null) {
				for (UIComponent ui : childs) {
					if (ui instanceof UISelectItems) {
						items = (UISelectItems) ui;
						break;
					} else if (ui instanceof UISelectItem) {
						UISelectItem item = (UISelectItem) ui;
						try {
							Object val = (Object) item.getItemValue();
							ret = getObject(val, arg2);
							if (ret != null) {
								break;
							}
						} catch (Exception e) {
						}
					}
				}
			}

			if (items != null) {
				List<Object> values = (List<Object>) items.getValue();
				if (values != null) {
					for (Object val : values) {
						ret = getObject(val, arg2);
						if (ret != null) {
							break;
						}
					}
				}
			}
		}
		return ret;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		String ret = "";
		if (arg2 != null && arg2 instanceof Country) {
			Country m = (Country) arg2;
			if (m != null) {
				Long id = m.getCountryId();
				if (id != null) {
					ret = id.toString();
				}
			}
		}
		if (arg2 != null && arg2 instanceof City) {
			City m = (City) arg2;
			if (m != null) {
				Long id = m.getCityId();
				if (id != null) {
					ret = id.toString();
				}
			}
		}
		return ret;
	}
	
	private Object getObject(Object val, String arg2) {
		Object ret = null;
		if (val instanceof Country) {
			Country b = (Country) val;
			if (arg2.equals("" + b.getCountryId())) {
				ret = b;
				return ret;
			}
		}
		if (val instanceof City) {
			City b = (City) val;
			if (arg2.equals("" + b.getCityId())) {
				ret = b;
				return ret;
			}
		}
		return ret;
	}
}
