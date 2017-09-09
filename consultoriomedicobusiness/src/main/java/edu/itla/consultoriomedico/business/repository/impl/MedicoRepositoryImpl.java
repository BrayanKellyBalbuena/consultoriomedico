package edu.itla.consultoriomedico.business.repository.impl;

import edu.itla.consultoriomedico.business.entity.Medico;
import edu.itla.consultoriomedico.business.repository.MedicoRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MedicoRepositoryImpl implements MedicoRepository {

    private Logger LOG = Logger.getLogger(MedicoRepositoryImpl.class.getName());
    private SessionFactory sessionFactory;

    public MedicoRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Medico entity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        LOG.log(Level.INFO, "Medico Guardado");
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Medico pac = findById(id);
        if (pac != null) {
            session.delete(pac);
            session.getTransaction().commit();
            session.close();
            LOG.log(Level.INFO, "Medico Eliminado");
        }
        ;

    }

    @Override
    public void update(Medico entity) {
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
    public Medico findById(Long id) {
        Session session = sessionFactory.openSession();
        Medico medico = (Medico) session.get(Medico.class, id);
        session.close();
        LOG.log(Level.INFO, "Encontrado");
        return medico;
    }

    @Override
    public List<Medico> findAll() {
        Session session = sessionFactory.openSession();
        List<Medico> medicos = session.createCriteria(Medico.class).list();
        return medicos;
    }
}
