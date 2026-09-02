package br.com.cursojsf;

import org.junit.jupiter.api.Test;

import jakarta.persistence.Persistence;

public class TesteJPA {
    
    @Test
    public void m() {
	Persistence.createEntityManagerFactory("meuprimeiroprojetojsf");
    }

}
