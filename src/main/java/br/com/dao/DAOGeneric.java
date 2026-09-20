package br.com.dao;

import java.util.List;

import br.com.entidades.Pessoa;
import br.com.jpa.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class DAOGeneric<E> {

    public void salvar(E entidade) {
	EntityManager entityManager = JPAUtil.getEntityManager();
	EntityTransaction entityTransaction = entityManager.getTransaction();
	entityTransaction.begin();
	entityManager.persist(entidade);
	entityTransaction.commit();
	entityManager.close();
    }

    public E merge(E entidade) {
	EntityManager entityManager = JPAUtil.getEntityManager();
	EntityTransaction entityTransaction = entityManager.getTransaction();
	entityTransaction.begin();
	E retorno = entityManager.merge(entidade);
	entityTransaction.commit();
	entityManager.close();

	return retorno;
    }

    public void delete(E entidade) {
	EntityManager entityManager = JPAUtil.getEntityManager();
	EntityTransaction entityTransaction = entityManager.getTransaction();
	entityTransaction.begin();
	Object entidadeGerenciada = entityManager.merge(entidade);
	entityManager.remove(entidadeGerenciada);
	entityTransaction.commit();
	entityManager.close();
    }

    @SuppressWarnings("unchecked")
    public List<E> getListEntity(Class<E> entidade) {
	EntityManager entityManager = JPAUtil.getEntityManager();
	EntityTransaction entityTransaction = entityManager.getTransaction();
	entityTransaction.begin();
	List<E> retorno = entityManager.createQuery("from " + entidade.getName()).getResultList();
	entityTransaction.commit();
	entityManager.close();
	return retorno;
    }

}
