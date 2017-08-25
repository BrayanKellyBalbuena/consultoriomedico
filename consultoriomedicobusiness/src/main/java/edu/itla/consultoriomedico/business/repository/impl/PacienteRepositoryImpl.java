package edu.itla.consultoriomedico.business.repository.impl;

import edu.itla.consultoriomedico.business.entity.Paciente;
import edu.itla.consultoriomedico.business.repository.PacienteRepository;
import edu.itla.consultoriomedico.business.util.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PacienteRepositoryImpl implements PacienteRepository {

    private List<Paciente> pacienteList=new ArrayList<>();
    private Logger LOG = Logger.getLogger(PacienteRepositoryImpl.class.getName());
    private PreparedStatement ps;

    public PacienteRepositoryImpl() {

    }

    @Override
    public void save(Paciente entity) {
        Connection connection = null;

        try {
            connection = new ConnectionDB().getConnection();
            String QUERY = "INSERT INTO paciente(nombre, apellido, correo) values (?,?,?)";
            ps = connection.prepareStatement(QUERY);
            ps.setString(1, entity.getNombre());
            ps.setString(2, entity.getApellido());
            ps.setString(3, entity.getCorreo());
            ps.executeUpdate();

            LOG.log(Level.INFO, "Paciente guardado cpm exito");

        } catch (ClassNotFoundException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, e.getMessage(), e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, e.getMessage(), e);
            }
        }

    }

    @Override
    public void update(Paciente entity) {
        int index=pacienteList.indexOf(entity);
        pacienteList.set(index,entity);
    }

    @Override
    public Paciente findById(Long id) {
        Connection connection = null;
        Paciente paciente = null;
        try {
            connection = new ConnectionDB().getConnection();
            String QUERY = "SELECT * FROM paciente WHERE id_paciente = ?";
            ps = connection.prepareStatement(QUERY);
            ps.setLong(1, id);
            ResultSet res = ps.executeQuery();
            res.next();

            paciente = getPaciente(res);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, e.getMessage(), e);
            }
        }
        return paciente;
    }


    private Paciente getPaciente(ResultSet res) throws SQLException {
        Paciente paciente = new Paciente();

        paciente.setId(res.getLong("id_paciente"));
        paciente.setNombre(res.getString("nombre"));
        paciente.setNombre(res.getString("apellido"));
        paciente.setApellido(res.getString("correo"));

        return paciente;
    }

    private List<Paciente> getPacientess(ResultSet res) throws SQLException {
        List<Paciente> pacientes = new ArrayList<>();
        Paciente paciente = null;
        while (res.next()) {
            paciente = new Paciente();
            paciente.setId(res.getLong("id_paciente"));
            paciente.setNombre(res.getString("nombre"));
            paciente.setApellido(res.getString("apellido"));
            paciente.setCorreo(res.getString("correo"));

            pacientes.add(paciente);
        }
        return pacientes;
    }

    @Override
    public List<Paciente> findAll() {
        Connection connection = null;
        List<Paciente> pacientes = null;
        try {
            connection = new ConnectionDB().getConnection();
            String QUERY = "SELECT * FROM paciente";
            ps = connection.prepareStatement(QUERY);
            ResultSet res = ps.executeQuery();

            pacientes = getPacientess(res);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, e.getMessage(), e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, e.getMessage(), e);
            }
        }
        return pacienteList = pacientes;
    }
}
