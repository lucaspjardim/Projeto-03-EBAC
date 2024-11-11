package org.br.lucaspjardim.dao.generic;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

/**
 * Author: Lucas Jardim
 */
public abstract class GenericDAO<T> implements IGenericDAO<T> {

    private final EntityManager entityManager;
    private final Class<T> entityClass;

    protected GenericDAO(EntityManager entityManager, Class<T> entityClass) {
        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }

    public void cadastrar(T entity) {
        EntityTransaction tx = entityManager.getTransaction();
        if (!tx.isActive()) {
            tx.begin();
        }
        try {
            entityManager.persist(entity);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    @Override
    public void atualizar(T entity) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            // Verifique se a transação não está ativa antes de iniciar uma nova
            if (!tx.isActive()) {
                tx.begin();
            }
            entityManager.merge(entity);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        }
    }


    @Override
    public void deletar(Long id) {
        T entity = entityManager.find(entityClass, id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    @Override
    public T buscarPorId(Long id) {
        return entityManager.find(entityClass, id);
    }

    @Override
    public List<T> buscarTodos() {
        return entityManager.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass)
                .getResultList();
    }

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }
}
