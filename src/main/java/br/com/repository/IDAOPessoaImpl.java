package br.com.repository;

import br.com.entidades.Pessoa;
import br.com.jpa.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class IDAOPessoaImpl implements IDAOPessoa {

    @Override
    public Pessoa consultarUsuario(String login, String senha) {
	Pessoa pessoa = null;
	EntityManager entityManager = JPAUtil.getEntityManager();
	EntityTransaction entityTransaction = entityManager.getTransaction();
	entityTransaction.begin();
	pessoa = (Pessoa) entityManager.createQuery("select p from Pessoa p where p. login = '" + login + "' and p.senha = '" + senha + "'").setMaxResults(1).getSingleResult();
	
	entityTransaction.commit();
	entityManager.close();
	return pessoa;
    }

}
