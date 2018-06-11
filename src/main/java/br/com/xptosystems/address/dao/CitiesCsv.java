package br.com.xptosystems.address.dao;

import br.com.xptosystems.address.Cities;
import br.com.xptosystems.address.UfComparator;
import br.com.xptosystems.utils.Paths;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CitiesCsv {

    public List<Cities> capitals() {
        try {
            List<Cities> list = this.db();
            List<Cities> newList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getCapital()) {
                    newList.add(list.get(i));
                }
            }
            return newList;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List min_max_cities_by_state() {
        List<Ufs> listUfAcumulador = new ArrayList();
        try {
            List<Cities> list = this.db();
            Collections.sort(list, new UfComparator());
            Map<String, Integer> map = new LinkedHashMap<>();
            String uf = "";
            int count = 1;
            for (int i = 0; i < list.size(); i++) {
                if (uf.isEmpty() || !uf.toUpperCase().equals(list.get(i).getUf().toUpperCase())) {
                    map.put(list.get(i).getUf().toUpperCase(), count);
                    count = 1;
                    uf = list.get(i).getUf().toUpperCase();
                } else {
                    count++;
                    map.put(list.get(i).getUf().toUpperCase(), count);
                }
            }
            Ufs ufFirst = new Ufs();
            Ufs ufLast = new Ufs();
            int z = 0;
            // MAIOR
            String uf_maior = "";
            int ct_maior = 0;
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (ct_maior == 0) {
                    ct_maior = entry.getValue();
                    uf_maior = entry.getKey();
                } else {
                    if (entry.getValue() > ct_maior) {
                        uf_maior = entry.getKey();
                        ct_maior = entry.getValue();
                    }
                }
            }
            String uf_menor = "";
            int ct_menor = 0;
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (ct_menor == 0) {
                    ct_menor = entry.getValue();
                    uf_menor = entry.getKey();
                } else {
                    if (entry.getValue() < ct_menor) {
                        uf_menor = entry.getKey();
                        ct_menor = entry.getValue();
                    }
                }
            }
            ufFirst = new Ufs("min", ct_menor, uf_menor);
            ufLast = new Ufs("max", ct_maior, uf_maior);
            listUfAcumulador.add(ufFirst);
            listUfAcumulador.add(ufLast);
            return listUfAcumulador;
        } catch (Exception e) {
        }

        return listUfAcumulador;
    }

    public Integer count_cities_by_state(String uf) {
        Integer count = 0;
        try {
            List<Cities> list = this.db();
            List<Cities> newList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getUf().toUpperCase().equals(uf.toUpperCase())) {
                    count++;
                }
            }
        } catch (Exception e) {
            return 0;
        }
        return count;
    }

    public Cities find_by_ibge_id(String code) {
        try {
            Cities cities = new Cities();
            List<Cities> list = this.db();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getIbge_id().equals(Long.parseLong(code))) {
                    cities = list.get(i);
                    break;
                }
            }
            return cities;
        } catch (Exception e) {
            return new Cities();
        }
    }

    public List<Cities> find_by_uf(String uf) {
        try {
            List<Cities> list = this.db();
            List<Cities> newList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getUf().toUpperCase().equals(uf.toUpperCase())) {
                    newList.add(list.get(i));
                }
            }
            return newList;
        } catch (Exception e) {
            return new ArrayList();
        }
    }

    public List<Cities> find(String column, String query) {
        try {
            if (column == null || column.isEmpty()) {
                return new ArrayList();
            }
            if (query == null || query.isEmpty()) {
                return new ArrayList();
            }
            List<Cities> list = this.db();
            List<Cities> newList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                switch (column) {
                    case "ibde_id":
                        try {
                            if (list.get(i).getIbge_id() == Long.parseLong(query)) {
                                newList.add(list.get(i));
                            }
                        } catch (Exception e) {

                        }
                        break;
                    case "uf":
                        try {
                            if (list.get(i).getUf().toUpperCase().equals(query.toUpperCase())) {
                                newList.add(list.get(i));
                            }
                        } catch (Exception e) {

                        }
                        break;
                    case "name":
                        try {
                            if (list.get(i).getName().toUpperCase().contains(query.toUpperCase()) || list.get(i).getName().toUpperCase().endsWith(query.toUpperCase())) {
                                newList.add(list.get(i));
                            }
                        } catch (Exception e) {

                        }
                        break;
                    case "lon":
                        try {
                            if (list.get(i).getLon() == Double.parseDouble(query)) {
                                newList.add(list.get(i));
                            }
                        } catch (Exception e) {

                        }
                        break;
                    case "lat":
                        try {
                            if (list.get(i).getLat() == Double.parseDouble(query)) {
                                newList.add(list.get(i));
                            }
                        } catch (Exception e) {

                        }
                        break;
                    case "no_accents":
                        try {
                            if (list.get(i).getNo_accents().toUpperCase().equals(query.toUpperCase())) {
                                newList.add(list.get(i));
                            }
                        } catch (Exception e) {

                        }
                        break;
                    case "alternative_names":
                        try {
                            if (list.get(i).getAlternative_names().toUpperCase().equals(query.toUpperCase())) {
                                newList.add(list.get(i));
                            }
                        } catch (Exception e) {

                        }
                        break;
                    case "micro_region":
                        try {
                            if (list.get(i).getMicro_region().toUpperCase().equals(query.toUpperCase())) {
                                newList.add(list.get(i));
                            }
                        } catch (Exception e) {

                        }
                        break;
                    case "meso_region":
                        try {
                            if (list.get(i).getMeso_region().toUpperCase().equals(query.toUpperCase())) {
                                newList.add(list.get(i));
                            }
                        } catch (Exception e) {

                        }
                        break;
                    default:
                        break;
                }
            }
            return newList;
        } catch (Exception e) {
            return new ArrayList();
        }
    }

    public String store(Cities c) {
        try {
            List<Cities> list = this.db();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getUf().toUpperCase().equals(c.getUf().toUpperCase()) && list.get(i).getName().toUpperCase().equals(c.getName().toUpperCase())) {
                    return "Cidade já cadastrada!";
                }
                if (Objects.equals(list.get(i).getIbge_id(), c.getIbge_id())) {
                    return "Código do IBGE já cadastrado!";
                }
            }
//            try (
//                    Reader reader = Files.newBufferedReader(Paths.);
//                    CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);) {
//                for (CSVRecord csvRecord : csvParser) {
//                    // Accessing Values by Column Index
//                    String name = csvRecord.get(0);
//                    String email = csvRecord.get(1);
//                    String phone = csvRecord.get(2);
//                    String country = csvRecord.get(3);
//                    
//                    System.out.println("Record No - " + csvRecord.getRecordNumber());
//                    System.out.println("---------------");
//                    System.out.println("Name : " + name);
//                    System.out.println("Email : " + email);
//                    System.out.println("Phone : " + phone);
//                    System.out.println("Country : " + country);
//                    System.out.println("---------------\n\n");
//                }
//            }
            return "";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String delete(Cities c) {
        try {
            Boolean delete = false;
            List<Cities> list = this.db();
            for (int i = 0; i < list.size(); i++) {
                if (Objects.equals(list.get(i).getIbge_id(), c.getIbge_id())) {
                    delete = true;
                }
            }
            if (!delete) {
                return "Código do IBGE não existe!";
            }
            return null;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private List<Cities> db() {
        List<Cities> listCities = new ArrayList();
        String citiesCsv = local();
        File citiesCsvFile = new File(citiesCsv);
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {

            br = new BufferedReader(new FileReader(citiesCsvFile));
            while ((line = br.readLine()) != null) {

                try {
                    // use comma as separator
                    String[] cities = line.split(cvsSplitBy);

                    Cities c = new Cities();
                    c.setIbge_id(Long.parseLong(cities[0]));
                    c.setUf(cities[1]);
                    c.setName(cities[2]);
                    c.setCapital(Boolean.parseBoolean(cities[3]));
                    c.setLon(Double.parseDouble(cities[4]));
                    c.setLat(Double.parseDouble(cities[5]));
                    c.setNo_accents(cities[6]);
                    c.setAlternative_names(cities[7]);
                    c.setMicro_region(cities[8]);
                    c.setMeso_region(cities[9]);
                    listCities.add(c);
                } catch (Exception e) {

                }

            }
            return listCities;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return listCities;
    }

    public Integer total() {
        Integer total = 0;
        try {
            List<Cities> list = this.db();
            return list.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public Integer total(String column) {
        try {
            if (column == null || column.isEmpty()) {
                return 0;
            }
            Map<String, String> map = new LinkedHashMap<>();
            List<Cities> list = this.db();
            List<Cities> newList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                switch (column) {
                    case "ibde_id":
                        try {
                            map.put(list.get(i).getIbge_id() + "", list.get(i).getIbge_id() + "");
                        } catch (Exception e) {

                        }
                        break;
                    case "uf":
                        try {
                            map.put(list.get(i).getUf().toUpperCase(), list.get(i).getUf().toUpperCase());
                        } catch (Exception e) {

                        }
                        break;
                    case "cities":
                        try {
                            map.put(list.get(i).getName().toUpperCase(), list.get(i).getName().toUpperCase());
                        } catch (Exception e) {

                        }
                        break;
                    case "lon":
                        try {
                            map.put(list.get(i).getLon() + "", list.get(i).getLon() + "");
                        } catch (Exception e) {

                        }
                        break;
                    case "lat":
                        try {
                            map.put(list.get(i).getLat() + "", list.get(i).getLat() + "");
                        } catch (Exception e) {

                        }
                        break;
                    case "no_accents":
                        try {
                            map.put(list.get(i).getNo_accents().toUpperCase(), list.get(i).getNo_accents().toUpperCase());
                        } catch (Exception e) {

                        }
                        break;
                    case "alternative_names":
                        try {
                            map.put(list.get(i).getAlternative_names().toUpperCase(), list.get(i).getAlternative_names().toUpperCase());
                        } catch (Exception e) {

                        }
                        break;
                    case "micro_region":
                        try {
                            map.put(list.get(i).getMicro_region().toUpperCase(), list.get(i).getMicro_region().toUpperCase());
                        } catch (Exception e) {

                        }
                        break;
                    case "meso_region":
                        try {
                            map.put(list.get(i).getMeso_region().toUpperCase(), list.get(i).getMeso_region().toUpperCase());
                        } catch (Exception e) {

                        }
                        break;
                    default:
                        break;
                }
            }
            return map.size();
        } catch (Exception e) {
            return 0;
        }
    }

    private String local() {
        try {
            return new Paths().get() + File.separator + "resources" + File.separator + "database" + File.separator + "cities.csv";
        } catch (Exception e) {
            return null;
        }
    }

    public class Ufs {

        private String type;
        private String name;
        private Integer amount_cities;

        public Ufs() {
            this.type = "";
            this.name = "";
            this.amount_cities = 0;
        }

        public Ufs(String type, Integer amount_cities, String name) {
            this.type = type;
            this.amount_cities = amount_cities;
            this.name = name;
        }

        public Integer getAmount_cities() {
            return amount_cities;
        }

        public void setAmount_cities(Integer amount_cities) {
            this.amount_cities = amount_cities;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

    }

}
