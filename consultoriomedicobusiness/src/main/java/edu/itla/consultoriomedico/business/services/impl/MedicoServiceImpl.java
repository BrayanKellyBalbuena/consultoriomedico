package edu.itla.consultoriomedico.business.services.impl;

import edu.itla.consultoriomedico.business.entity.Medico;
import edu.itla.consultoriomedico.business.repository.MedicoRepository;
import edu.itla.consultoriomedico.business.services.MedicoService;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class MedicoServiceImpl implements MedicoService {

    private MedicoRepository medicoRepository;
    private Logger LOG = Logger.getLogger(MedicoServiceImpl.class.getName());

    public MedicoServiceImpl(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @Override
    public void save(Medico entity) {
        if (entity != null) {
            medicoRepository.save(entity);
            LOG.log(Level.INFO, "Intentanto guardar");
        }

    }

    @Override
    public void delete(Long id) {
        medicoRepository.delete(id);
    }

    @Override
    public void update(Medico entity) {
        medicoRepository.update(entity);
    }

    @Override
    public Medico findById(Long id) {
        return medicoRepository.findById(id);
    }

    @Override
    public List<Medico> findAll() {
        return medicoRepository.findAll();
    }
}
