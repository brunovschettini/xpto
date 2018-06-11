package br.com.xptosystems.address;

import java.util.Comparator;

public class UfComparator implements Comparator<Cities> {

    @Override
    public int compare(Cities o1, Cities o2) {
        return o1.getUf().compareTo(o2.getUf());
    }
}
