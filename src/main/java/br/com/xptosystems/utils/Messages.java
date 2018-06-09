package br.com.xptosystems.utils;

import bootfacesbr.Bootstrap;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class Messages {

 public static void error(String title, String description) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, title, description));
    }

    public static void error(String title, String description, Boolean template) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, title, description));
        Bootstrap.update("ui_sf_message_error");
    }

    public static void fatal(String title, String description) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, title, description));
    }

    public static void fatal(String title, String description, Boolean template) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, title, description));
        Bootstrap.update("ui_sf_message_fatal");
    }

    public static void info(String title, String description) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, title, description));
    }

    public static void info(String title, String description, Boolean template) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, title, description));
        Bootstrap.update("ui_sf_message_info");

    }

    public static void warn(String title, String description) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, title, description));
    }

    public static void warn(String title, String description, Boolean template) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, title, description));
        Bootstrap.update("ui_sf_message_warn");
    }
}
