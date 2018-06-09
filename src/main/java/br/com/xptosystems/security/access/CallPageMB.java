package br.com.xptosystems.security.access;

import br.com.xptosystems.utils.Sessions;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class CallPageMB implements Serializable {

    private String returnPage = "";

    /**
     * <p>
     * <strong>page</strong></p>
     * <p>
     * <strong>Exemplo:</strong> page("pesquisaAgendaTelefone"); Não é
     * necessário no Bean espefícar o nome completo.</p>
     *
     * @param page (Nome da página)
     *
     * @author Bruno
     * @return
     * @throws java.io.IOException
     */
    public synchronized String page(String page) throws IOException {
        Sessions.remove(page);
        Sessions.remove(page + "MB");
        Sessions.remove(page + "Bean");
        Sessions.remove(page + "Controller");
        try {
            try {
                return page;
            } catch (Exception ex) {
                try {
                    return page;
                } catch (Exception ex2) {

                }
            }
        } catch (Exception e) {
            // FacesContext.getCurrentInstance().getExternalContext().dispatch(page + ".xhtml");
        }
        return null;
        //return page;
    }

    public synchronized String page(String page, String bean) throws IOException {
        Sessions.remove(bean);
        try {
            try {
                return page;
            } catch (Exception ex) {
                try {
                    return page;
                } catch (Exception ex2) {

                }
            }
        } catch (Exception e) {
            // FacesContext.getCurrentInstance().getExternalContext().dispatch(page + ".xhtml");
        }
        return null;
    }

    public String getReturnPage() {
        if (Sessions.exists("return_page")) {
            return Sessions.getString("return_page");
        }
        return returnPage;
    }

    public void setReturnPage(String returnPage) {
        this.returnPage = returnPage;
    }
}
