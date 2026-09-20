package br.com.repository;

import java.util.List;

import br.com.entidades.Pessoa;
import br.com.jpa.JPAUtil;
import jakarta.persistence.EntityManager;

public class IDAOPessoaImpl implements IDAOPessoa {

    @Override
    public Pessoa consultar(String login, String senha) {
	EntityManager entityManager = JPAUtil.getEntityManager();

	List<Pessoa> resultados = entityManager
		.createQuery("select p from Pessoa p where p.login = :login and p.senha = :senha", Pessoa.class)
		.setParameter("login", login).setParameter("senha", senha).setMaxResults(1) // Protege o banco forçando
											    // o retorno de no máximo 1
											    // registro
		.getResultList();

	entityManager.close();

	// Se a lista estiver vazia (usuário não encontrado), retorna null.
	// Caso contrário, pega o primeiro e único usuário da lista.
	return resultados.isEmpty() ? null : resultados.get(0);
    }

}
