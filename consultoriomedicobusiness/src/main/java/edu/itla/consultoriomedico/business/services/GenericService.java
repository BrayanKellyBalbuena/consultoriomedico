package edu.itla.consultoriomedico.business.services;

import java.io.Serializable;
import java.util.List;

public interface GenericService<T, ID extends Serializable> {
    void save(T entity);

    void delete(ID id);

    void update(T entity);

    T findById(ID id);

    List<T> findAll();
}
