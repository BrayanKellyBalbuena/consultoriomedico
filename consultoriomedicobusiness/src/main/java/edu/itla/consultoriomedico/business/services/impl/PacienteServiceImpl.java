package edu.itla.consultoriomedico.business.services.impl;

import edu.itla.consultoriomedico.business.entity.Paciente;
import edu.itla.consultoriomedico.business.repository.PacienteRepository;
import edu.itla.consultoriomedico.business.services.PacienteService;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PacienteServiceImpl implements PacienteService {

    private PacienteRepository pacienteRepository;
    private Logger LOG = Logger.getLogger(PacienteServiceImpl.class.getName());

    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public void save(Paciente entity) {
        if (entity != null) {
            pacienteRepository.save(entity);
            LOG.log(Level.INFO, "Intentanto guardar");
        }

    }

    @Override
    public void delete(Long id) {
        pacienteRepository.delete(id);
    }

    @Override
    public void update(Paciente entity) {
        pacienteRepository.update(entity);
    }

    @Override
    public Paciente findById(Long id) {
        return pacienteRepository.findById(id);
    }

    @Override
    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }
}
