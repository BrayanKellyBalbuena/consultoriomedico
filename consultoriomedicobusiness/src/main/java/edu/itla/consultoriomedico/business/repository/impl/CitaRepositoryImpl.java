package edu.itla.consultoriomedico.business.repository.impl;

import edu.itla.consultoriomedico.business.entity.Cita;
import edu.itla.consultoriomedico.business.repository.CitaRepository;

import java.util.ArrayList;
import java.util.List;

public class CitaRepositoryImpl implements CitaRepository {

    private List<Cita> citaList = new ArrayList<>();

    @Override
    public void save(Cita entity) {
        citaList.add(entity);
    }

    @Override
    public void update(Cita entity) {
        int index=citaList.indexOf(entity);
        citaList.set(index,entity);
    }

    @Override
    public Cita findById(Long id) {
        return citaList.get(id.intValue());
    }

    @Override
    public List<Cita> findAll() {
        return citaList;
    }
}
