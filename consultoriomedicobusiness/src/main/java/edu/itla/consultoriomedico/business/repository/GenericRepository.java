/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.itla.consultoriomedico.business.repository;

import java.util.List;

/**
 *
 * @author bkelly
 */
public interface GenericRepository<T, ID> {
    void save(T entity);

    void delete(ID id);
    void update(T entity);
    T findById(ID id);
    List<T> findAll();
}
