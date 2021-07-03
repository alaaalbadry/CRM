package com.techvalueinsight.jsf.primefaces.view.helpers;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JSFErrorMessageHelper {

	public static void addErrorMessage(String summary, String msg) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, msg);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public static void addInfoMessage(String summary, String msg) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, msg);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public static void addWarnMessage(String summary, String msg) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, summary, msg);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
