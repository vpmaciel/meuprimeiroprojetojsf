package br.com.repository;

import java.util.List;

import br.com.entidades.Lancamento;
import br.com.jpa.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class IDAOLancamentoImpl implements IDAOLancamento {

    @Override
    public List<Lancamento> consultar(Long codUser) {
	List<Lancamento> lancamentos = null;
	EntityManager entityManager = JPAUtil.getEntityManager();
	EntityTransaction transaction = entityManager.getTransaction();
	transaction.begin();
	lancamentos = entityManager
		.createQuery("select l from Lancamento l where l.usuario.id = :idUsuario", Lancamento.class)
		.setParameter("idUsuario", codUser).getResultList();
	transaction.commit();
	entityManager.close();
	
	return lancamentos;
    }

}
