package edu.itla.consultoriomedico.business.util;

import edu.itla.consultoriomedico.business.entity.Paciente;
import edu.itla.consultoriomedico.business.enums.PathEnums;
import edu.itla.consultoriomedico.business.repository.PacienteRepository;
import edu.itla.consultoriomedico.business.repository.impl.PacienteRepositoryImpl;

import java.io.IOException;

public class PropertyTest {
    public static void main(String[] args){
        PacienteRepositoryImpl pp=null;
        try {
            pp = new PacienteRepositoryImpl();
            Paciente p = new Paciente(1,"mario","pepe",123);
            pp.crear(p);
            pp.saveData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println( pp.findAll().get(1).getId());
        System.out.println( PropertyPath.getProperties().get(PathEnums.DATA_PACIENTES));
        System.out.println( PropertyPath.getProperties().get(PathEnums.DATA_MEDICOS));
    }
}
