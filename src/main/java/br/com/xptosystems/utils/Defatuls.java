package br.com.xptosystems.utils;

import java.io.File;
import java.io.IOException;
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
public class Defatuls {

    private String host;
    private Integer port;

    public Defatuls() {
        this.host = "";
        this.port = 0;
    }

    public Defatuls(String host, Integer port) {
        this.host = host;
        this.port = port;
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
                json = FileUtils.readFileToString(file);
            } catch (IOException ex) {
                Logger.getLogger(Defatuls.class.getName()).log(Level.SEVERE, null, ex);
            }
            JSONObject jSONObject = new JSONObject(json);
            try {
                host = jSONObject.getString("host");
            } catch (Exception e) {

            }
            try {
                port = jSONObject.getInt("port");
            } catch (Exception e) {

            }
        } catch (JSONException ex) {

        }
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getURL() {
        loadJson();
        String url = "";
        if (!host.isEmpty()) {
            url += host;
        }
        if (port > 0 && port != 80) {
            url += ":" + port;
        }
        return url;
    }
}
