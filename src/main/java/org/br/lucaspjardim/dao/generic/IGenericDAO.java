package org.br.lucaspjardim.dao.generic;

import java.util.List;

/**
 * Author: Lucas Jardim
 */
public interface IGenericDAO<T> {
    void cadastrar(T entity);
    void atualizar(T entity);
    void deletar(Long id);
    T buscarPorId(Long id);
    List<T> buscarTodos();
}
