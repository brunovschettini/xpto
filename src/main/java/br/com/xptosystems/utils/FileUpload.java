package br.com.xptosystems.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.UUID;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;

public class FileUpload {

    public static void upload(String path, String filename, Part[] fileUpload) throws IOException {
        for (int i = 0; i < fileUpload.length; i++) {
            upload(path, filename, fileUpload[i]);
        }
    }

    public static void upload(String path, String filename, Part fileUpload) throws IOException {
        InputStream inputStream = fileUpload.getInputStream();
        final String partHeader = fileUpload.getHeader("content-disposition");
        String name = "";
        for (String content : fileUpload.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                name = content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        String resources = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath("") + File.separator + "resources" + File.separator + "clients" + File.separator + "xptosystems" + path;
        File fileResources = new File(resources);
        if (!fileResources.exists()) {
            fileResources.mkdirs();
        }
        File fileExists = new File(resources + File.separator + filename);
        if (fileExists.exists()) {
            fileExists.delete();
        }

        UUID uuidX = UUID.randomUUID();
        String uuid = uuidX.toString().replace("-", "").toUpperCase();
        BufferedReader br = null;
        File fos = null;
        OutputStream outputStream = null;

        try {
            // write the inputStream to a FileOutputStream
            fos = new File(fileResources + File.separator + filename);
            outputStream = new FileOutputStream(fos);

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

            System.out.println("Done!");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    // outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public static void importExternal(String path, String filename, String fileUri) throws IOException {
        File f = new File(fileUri);
        String resources = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath("") + File.separator + "resources" + File.separator + "client" + File.separator + "xptosystems" + path;
        File fileResources = new File(resources);
        if (!fileResources.exists()) {
            fileResources.mkdirs();
        }
        File fileExists = new File(resources + File.separator + filename);
        if (fileExists.exists()) {
            fileExists.delete();
        }

        try {
            URL url = new URL(fileUri);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            // con.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
            //        [{"key":"X-Cosmos-Token","value":"2dHdD6sYFC_ULHB-Ibrm4w","description":""}]
            con.setRequestProperty("X-Cosmos-Token", "2dHdD6sYFC_ULHB-Ibrm4w");
            // 15 Segundos
            con.setConnectTimeout(15000);
            con.setUseCaches(true);
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            con.setRequestProperty("Content-Type", "image/jpg");
            con.setDoInput(true);
            con.setDoOutput(true);
            int responseCode = con.getResponseCode();
            Charset charset = Charset.forName("UTF-8");
            InputStream is = con.getInputStream();
            OutputStream os = new FileOutputStream(resources + File.separator + filename);

            byte[] b = new byte[2048];
            int length;

            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }

            is.close();
            os.close();

        } catch (Exception e) {
            e.getMessage();
        }
    }
}
