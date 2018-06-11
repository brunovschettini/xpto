package br.com.xptosystems.address;

import java.util.Comparator;

public class CitiesComparator extends Cities implements Comparator {

    public int compare(Cities c1, Cities c2) {
        return c1.getName().compareTo(c2.getName());
    }

    @Override
    public int compare(Object o1, Object o2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
