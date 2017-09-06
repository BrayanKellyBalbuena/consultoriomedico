package edu.itla.consultoriomedico.business.repository.impl;

import edu.itla.consultoriomedico.business.entity.EspecialidadMedica;
import edu.itla.consultoriomedico.business.repository.EspecialidadRepository;

import java.util.ArrayList;
import java.util.List;

public class EspecialidadRepositoryImpl implements EspecialidadRepository {
    private List<EspecialidadMedica> especialidadMedicaList =new ArrayList<>();

    @Override
    public void save(EspecialidadMedica entity) {
        especialidadMedicaList.add(entity);
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void update(EspecialidadMedica entity) {
        int index= especialidadMedicaList.indexOf(entity);
        especialidadMedicaList.set(index,entity);
    }

    @Override
    public EspecialidadMedica findById(Long id) {
        return especialidadMedicaList.get(id.intValue());
    }

    @Override
    public List<EspecialidadMedica> findAll() {
        return especialidadMedicaList;
    }
}
