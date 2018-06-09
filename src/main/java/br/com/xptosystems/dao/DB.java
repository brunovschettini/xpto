package br.com.xptosystems.dao;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DB {

    // Carrega driver JDBC
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private EntityManager entidade;

    public EntityManager getEntityManager() {
        if (entidade == null) {
            try {
                Map properties = new HashMap();
                properties.put("allowMultiQueries", "true");
                properties.put("autoReconnect", "true");
                properties.put("checkoutTimeout", "1000");
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("xptop_systems", properties);
                String createTable = "";
                if (createTable.equals("criar")) {
                    // properties.put(EntityManagerFactoryProvider.DDL_GENERATION, EntityManagerFactoryProvider.CREATE_ONLY);
                }
                entidade = emf.createEntityManager();
            } catch (Exception e) {
                return null;
            }
        }
        return entidade;
    }

//    public EntityManager getEntityManager() {
//        if (entidade == null) {
//            try {
//                EntityManagerFactory emf = Persistence.createEntityManagerFactory("salaofacil");
//                entidade = emf.createEntityManager();
//            } catch (Exception e) {
//                return null;
//            }
//        }
//        return entidade;
//    }
}
