package edu.itla.consultoriomedico.business.services;

import edu.itla.consultoriomedico.business.enums.RepositoryEnum;
import edu.itla.consultoriomedico.business.factory.ApplicationContext;
import edu.itla.consultoriomedico.business.repository.PacienteRepository;
import edu.itla.consultoriomedico.business.entity.Paciente;

import java.util.List;

public class ConsultorioServices {

    private PacienteRepository pacienteRepository = (PacienteRepository)
            ApplicationContext.getRepository(RepositoryEnum.PACIENTE_REPOSITORY);


    public void savePaciente(Paciente paciente) {
        pacienteRepository.save(paciente);
    }

    public Paciente findProfesorById(Long id) {
        return pacienteRepository.findById(id);
    }

    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

}
