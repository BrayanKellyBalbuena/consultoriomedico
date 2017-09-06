package edu.itla.consultoriomedico.business.repository.impl;

import edu.itla.consultoriomedico.business.entity.Paciente;
import edu.itla.consultoriomedico.business.repository.PacienteRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PacienteRepositoryImpl implements PacienteRepository {

    private Logger LOG = Logger.getLogger(PacienteRepositoryImpl.class.getName());
    private SessionFactory sessionFactory;

    public PacienteRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Paciente entity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        LOG.log(Level.INFO, "Paciente Guardado");
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Paciente pac = findById(id);
        if (pac != null) {
            session.delete(pac);
            session.getTransaction().commit();
            session.close();
            LOG.log(Level.INFO, "Paciente Eliminado");
        }
        ;

    }

    @Override
    public void update(Paciente entity) {
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
    public Paciente findById(Long id) {
        Session session = sessionFactory.openSession();
        Paciente paciente = (Paciente) session.get(Paciente.class, id);
        session.close();
        LOG.log(Level.INFO, "Encontrado");
        return paciente;
    }

    @Override
    public List<Paciente> findAll() {
        Session session = sessionFactory.openSession();
        List<Paciente> pacientes = session.createCriteria(Paciente.class).list();
        return pacientes;
    }
}
