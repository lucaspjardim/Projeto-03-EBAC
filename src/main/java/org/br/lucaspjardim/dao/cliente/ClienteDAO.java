package org.br.lucaspjardim.dao.cliente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.br.lucaspjardim.dao.generic.GenericDAO;
import org.br.lucaspjardim.model.cliente.Cliente;

import java.util.List;

public class ClienteDAO extends GenericDAO<Cliente> {

    public ClienteDAO(EntityManager entityManager) {
        super(entityManager, Cliente.class); // Passando o EntityManager e a classe da entidade para o GenericDAO
    }

    public Cliente buscarPorEmail(String email) {
        try {
            return getEntityManager().createQuery("SELECT c FROM Cliente c WHERE c.email = :email", Cliente.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Cliente> buscarTodosClientes() {
        return getEntityManager().createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
    }
}
