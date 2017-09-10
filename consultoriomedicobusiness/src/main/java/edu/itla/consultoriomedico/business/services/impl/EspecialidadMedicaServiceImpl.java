package edu.itla.consultoriomedico.business.services.impl;

import edu.itla.consultoriomedico.business.entity.EspecialidadMedica;

import edu.itla.consultoriomedico.business.repository.EspecialidadMedicaRepository;
import edu.itla.consultoriomedico.business.repository.impl.EspecialidadMedicaRepositoryImpl;
import edu.itla.consultoriomedico.business.services.EspecialidadMedicaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class EspecialidadMedicaServiceImpl implements EspecialidadMedicaService {

    private EspecialidadMedicaRepository especialidadMedicaRepository;
    private Logger LOG = Logger.getLogger(MedicoServiceImpl.class.getName());

    public EspecialidadMedicaServiceImpl(EspecialidadMedicaRepository especialidadMedicaRepository) {
        this.especialidadMedicaRepository = especialidadMedicaRepository;
    }


    @Override
    public void save(EspecialidadMedica entity) {
        if (entity != null) {
            especialidadMedicaRepository.save(entity);
            LOG.log(Level.INFO, "Intentanto guardar");
        }

    }

    @Override
    public void delete(Long id) {
        especialidadMedicaRepository.delete(id);
    }

    @Override
    public void update(EspecialidadMedica entity) {
        especialidadMedicaRepository.update(entity);
    }

    @Override
    public EspecialidadMedica findById(Long id) {
        return especialidadMedicaRepository.findById(id);
    }

    @Override
    public List<EspecialidadMedica> findAll() {
        return especialidadMedicaRepository.findAll();
    }
}