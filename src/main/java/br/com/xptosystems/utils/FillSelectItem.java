package br.com.xptosystems.utils;

import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;

public class FillSelectItem {

    private Long id;
    private List<SelectItem> listClients;

    public FillSelectItem() {
        this.id = null;
        this.listClients = new ArrayList();
    }

    public FillSelectItem(Long id, List<SelectItem> listClients) {
        this.id = id;
        this.listClients = listClients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<SelectItem> getListClients() {
        return listClients;
    }

    public void setListClients(List<SelectItem> listClients) {
        this.listClients = listClients;
    }

}
