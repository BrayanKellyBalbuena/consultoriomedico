package edu.itla.consultoriomedico.business.repository.impl;

import edu.itla.consultoriomedico.business.entity.Paciente;
import edu.itla.consultoriomedico.business.enums.SerializadorEnum;
import edu.itla.consultoriomedico.business.repository.PacienteRepository;
import edu.itla.consultoriomedico.business.util.Serializador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PacienteRepositoryImpl implements PacienteRepository {

    private List<Paciente> pacienteList = new ArrayList<>() ;
    private Serializador<Paciente> sPaciente
            ;

    public PacienteRepositoryImpl() throws IOException, ClassNotFoundException {
        sPaciente = new Serializador<>(SerializadorEnum.PACIENTES);
        loadData();

    }

    @Override
    public void crear(Paciente entity) {
        this.pacienteList.add(entity);
    }

    @Override
    public void modificar(Paciente entity) {
        int index=pacienteList.indexOf(entity);
        pacienteList.set(index,entity);
    }

    @Override
    public Paciente findById(Long id) {
        return pacienteList.get(id.intValue());
    }

    @Override
    public List<Paciente> findAll() {
        return pacienteList;
    }

    private void loadData(){
        try {
            pacienteList = sPaciente.leerObjeto() !=  null? sPaciente.leerObjeto() : pacienteList;
        } catch (IOException e) {

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveData() throws IOException {
        sPaciente.escribirObjecto(pacienteList);
    }
}
