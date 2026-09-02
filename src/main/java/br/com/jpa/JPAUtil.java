package br.com.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    
    private static EntityManagerFactory factory = null;
    
    static {
	if(factory == null) {
	    factory = Persistence.createEntityManagerFactory("meuprimeiroprojetojsf");
	}
    }
    
    public static EntityManager getEntityManager() {
	return factory.createEntityManager();
    }

}
