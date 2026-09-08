package br.com.repository;

import br.com.entidades.Pessoa;
import br.com.jpa.JPAUtil;
import jakarta.persistence.EntityManager;

public class IDAOPessoaImpl implements IDAOPessoa {

    @Override
    public Pessoa consultarUsuario(String login, String senha) {
	Pessoa pessoa = null;
	EntityManager entityManager = JPAUtil.getEntityManager();
	pessoa = (Pessoa) entityManager.createQuery("select p from Pessoa p where p.login = '" + login + "' and p.senha = '" + senha + "'").setMaxResults(1).getSingleResult();
	

	entityManager.close();
	return pessoa;
    }

}
