package edu.itla.consultoriomedico.business.repository.impl;

import edu.itla.consultoriomedico.business.entity.Paciente;
import edu.itla.consultoriomedico.business.repository.PacienteRepository;

import java.util.ArrayList;
import java.util.List;

public class PacienteRepositoryImpl implements PacienteRepository {

    private List<Paciente> pacienteList=new ArrayList<>();

    @Override
    public void crear(Paciente entity) {
        pacienteList.add(entity);
    }

    @Override
    public void modificar(Paciente entity) {
        int index=pacienteList.indexOf(entity);
        pacienteList.set(index,entity);
    }

    @Override
    public Paciente findById(Integer id) {
        return pacienteList.get(id.intValue());
    }

    @Override
    public List<Paciente> findAll() {
        return pacienteList;
    }
}
