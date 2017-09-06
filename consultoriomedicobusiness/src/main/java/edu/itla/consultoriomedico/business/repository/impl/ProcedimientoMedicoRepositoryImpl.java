package edu.itla.consultoriomedico.business.repository.impl;

import edu.itla.consultoriomedico.business.entity.ProcedimientoMedico;
import edu.itla.consultoriomedico.business.repository.ProcedimientoMedicoRepository;

import java.util.ArrayList;
import java.util.List;

public class ProcedimientoMedicoRepositoryImpl implements ProcedimientoMedicoRepository {

    List<ProcedimientoMedico> listProcedimiento = new ArrayList<>();

    @Override
    public void save(ProcedimientoMedico entity) {
        listProcedimiento.add(entity);
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void update(ProcedimientoMedico entity) {
        int index = listProcedimiento.indexOf(entity);
        if(index != -1) {
            listProcedimiento.set(index, entity);
        }

    }

    @Override
    public ProcedimientoMedico findById(Long id) {
        return listProcedimiento.get(id.intValue());
    }

    @Override
    public List<ProcedimientoMedico> findAll() {
        return listProcedimiento;
    }
}
