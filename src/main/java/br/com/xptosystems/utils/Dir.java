package br.com.xptosystems.utils;

import java.io.File;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

public class Dir {

    public static boolean create(String diretorio) {
        try {
            String s[] = diretorio.split("/");
            boolean err = false;
            String caminhoContac = "";
            int b = 0;
            String caminho;
            for (String item : s) {
                if (!item.equals("")) {
                    if (b == 0) {
                        caminhoContac = item;
                        caminho = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath("/" + caminhoContac);
                    } else {
                        caminhoContac = "/" + caminhoContac + "/" + item;
                        caminho = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath(caminhoContac);
                    }
                    if (!new File(caminho).exists()) {
                        File file = new File(caminho);
                        if (!file.mkdir()) {
                            err = false;
                            break;
                        }
                    }
                    b++;
                }
            }
            return !err;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean remove(String diretorio) {
        if (new File(((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath(diretorio)).exists()) {
            File file = new File(((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath(diretorio));
            if (file.delete()) {
                return true;
            }
        }
        return false;
    }

    public static String file(String diretorio, String arquivo) {
        if (arquivo.isEmpty()) {
            return null;
        }
        String caminho = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath(diretorio);
        try {
            File files = new File(caminho);
            if (!files.exists()) {
                return null;
            }
            return caminho;
        } catch (Exception e) {
            return null;
        }
    }

}
