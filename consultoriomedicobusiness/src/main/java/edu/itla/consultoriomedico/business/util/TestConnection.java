package edu.itla.consultoriomedico.business.util;

import edu.itla.consultoriomedico.business.entity.Paciente;
import edu.itla.consultoriomedico.business.services.ConsultorioServices;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConnection {

    public static void main(String[] args) {
        ConsultorioServices services = new ConsultorioServices();

        Connection con = null;
        try {
            con = new ConnectionDB().getConnection();
            System.out.println(con);

           /* Paciente p = services.findProfesorById(new Long(1));
            p.setNombre("Jose");
            p.setApellido("Perez");
            p.setCorreo("pre@p");
            services.savePaciente(p);*/
            services.findAll().forEach(x -> System.out.println(x.getNombre() + "- " + x.getId()));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
