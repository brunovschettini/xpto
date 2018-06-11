//package br.com.xptosystems.beans;
//
//import br.com.xptosystems.address.Cities;
//import br.com.xptosystems.security.ws.ImportCitiesWS;
//import br.com.xptosystems.utils.FileUpload;
//import br.com.xptosystems.utils.Messages;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.security.NoSuchAlgorithmException;
//import java.util.ArrayList;
//import java.util.List;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ViewScoped;
//import javax.faces.context.FacesContext;
//import javax.servlet.ServletContext;
//import javax.servlet.http.Part;
//
//@ManagedBean
//@ViewScoped
//public class ImportCitiesMB implements Serializable {
//
//    private Part fileUpload;
//
//    private List<Cities> listCsvCities;
//
//    public ImportCitiesMB() {
//        listCsvCities = new ArrayList();
//    }
//
//    public synchronized void process() throws NoSuchAlgorithmException {
//        new ImportCitiesWS().auth(listCsvCities);
//    }
//
//    public Part getFileUpload() {
//        return fileUpload;
//    }
//
//    public void setFileUpload(Part fileUpload) {
//        this.fileUpload = fileUpload;
//    }
//
//    public void upload() throws IOException {
//        if (!fileUpload.getContentType().equals("text/csv")) {
//            Messages.warn("Validação", "Arquivo inválido. Deve ser enviado o arquivo do tipo CSV!");
//            return;
//        }
//
//        InputStream input = null;
//        OutputStream output = null;
//
//
//
//        System.out.println("file size: " + fileUpload.getSize());
//        System.out.println("file type: " + fileUpload.getContentType());
//        System.out.println("file info: " + fileUpload.getHeader("Content-Disposition"));
//        fileUpload = null;
//        // FileUpload.upload(fileUpload);
//    }
//
//    public Boolean getExists() {
//        if (fileUpload != null) {
//            return true;
//        }
//        return false;
//    }
//
//    public List<Cities> getListCsvCities() {
//        return listCsvCities;
//    }
//
//    public void setListCsvCities(List<Cities> listCsvCities) {
//        this.listCsvCities = listCsvCities;
//    }
//
//}
