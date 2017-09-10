package edu.itla.consultoriomedico.business.repository.impl;

import edu.itla.consultoriomedico.business.entity.EspecialidadMedica;
import edu.itla.consultoriomedico.business.repository.EspecialidadMedicaRepository;
import edu.itla.consultoriomedico.business.repository.GenericRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class EspecialidadMedicaRepositoryImpl implements EspecialidadMedicaRepository{

    private Logger LOG = Logger.getLogger(EspecialidadMedicaRepositoryImpl.class.getName());
    private SessionFactory sessionFactory;

    public EspecialidadMedicaRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(EspecialidadMedica entity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        LOG.log(Level.INFO, "Especialidad Medica Guardado");
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        EspecialidadMedica pac = findById(id);
        if (pac != null) {
            session.delete(pac);
            session.getTransaction().commit();
            session.close();
            LOG.log(Level.INFO, "EspecialidadMedica Eliminado");
        }
        ;

    }

    @Override
    public void update(EspecialidadMedica entity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        if (findById(entity.getId()) != null) {
            session.update(entity);
            session.getTransaction().commit();
            session.close();
            LOG.log(Level.INFO, "Actualizado");
        }
    }

    @Override
    public EspecialidadMedica findById(Long id) {
        Session session = sessionFactory.openSession();
        EspecialidadMedica especialidadMedica = (EspecialidadMedica) session.get(EspecialidadMedica.class, id);
        session.close();
        LOG.log(Level.INFO, "Encontrado");
        return especialidadMedica;
    }

    @Override
    public List<EspecialidadMedica> findAll() {
        Session session = sessionFactory.openSession();
        List<EspecialidadMedica> especialidad = session.createCriteria(EspecialidadMedica.class).list();
        return especialidad;
    }
}
