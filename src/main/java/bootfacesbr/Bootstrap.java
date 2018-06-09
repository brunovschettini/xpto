package bootfacesbr;

import java.util.Collection;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
//import org.primefaces.context.RequestContext;

public class Bootstrap {

    private static final Logger LOG = Logger.getLogger(Bootstrap.class.getName());
    private Integer showMessage = null;

    /**
     * UPDATE Atualiza um único componente
     *
     * @param string
     */
    public static void update(String string) {
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(string);

    }

    /**
     * UPDATE Atualiza uma coleção de componentes
     *
     * @param c
     */
    public static void update(Collection c) {
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().addAll(c);
    }

    /**
     * EXECUTE Executa uma ação no componente referenciado
     *
     * @param string
     */
    public static void execute(String string) {
        try {
            // RequestContext.getCurrentInstance().execute(string);
        } catch (Exception e) {
        }
    }

    /**
     * Open DIALOG Abre o dialog
     *
     * @param string
     */
    public static void openDialog(String string) {
        // RequestContext.getCurrentInstance().execute("PF('" + string + "').show()");
    }

    /**
     * Close DIALOG Fecha o dialog
     *
     * @param string
     */
    public static void closeDialog(String string) {
        // RequestContext.getCurrentInstance().execute("PF('" + string + "').hide()");
    }

    public static void scrollTo(String string) {
        // RequestContext.getCurrentInstance().scrollTo(string);
    }

    public static void scrollTo(String string, Object o) {
        // RequestContext.getCurrentInstance().addCallbackParam(string, o);
    }

    /**
     * RESET Limpa um único componente
     *
     * @param string
     */
    public static void reset(String string) {
        // RequestContext.getCurrentInstance().reset(string);
    }

    /**
     * RESET Limpa um ou mais componentes
     *
     * @param c
     */
    public static void reset(Collection c) {
        // RequestContext.getCurrentInstance().reset(c);
    }

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

    public Integer getShowMessage() {
        return showMessage;
    }

    public void setShowMessage(Integer showMessage) {
        this.showMessage = showMessage;
    }
}
