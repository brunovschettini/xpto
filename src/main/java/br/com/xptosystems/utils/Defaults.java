package br.com.xptosystems.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;

@ManagedBean
@ViewScoped
public class Defaults {

    public static String WEBSERVICE;

    public Defaults() {
        WEBSERVICE = "";
    }

    public Defaults(String webservice) {
        WEBSERVICE = webservice;
    }

    public void loadJson() {
        FacesContext faces = FacesContext.getCurrentInstance();
        try {
            File file = new File(((ServletContext) faces.getExternalContext().getContext()).getRealPath("/resources/conf/defaults.json"));
            if (!file.exists()) {
                return;
            }
            String json = null;
            try {
                json = FileUtils.readFileToString(file, Charset.defaultCharset());
            } catch (IOException ex) {
                Logger.getLogger(Defaults.class.getName()).log(Level.SEVERE, null, ex);
            }
            JSONObject jSONObject = new JSONObject(json);
            try {
                WEBSERVICE = jSONObject.getString("webservice");
            } catch (Exception e) {

            }
        } catch (JSONException ex) {

        }
    }

}
