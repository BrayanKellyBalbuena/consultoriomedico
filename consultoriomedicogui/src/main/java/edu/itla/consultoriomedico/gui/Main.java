package edu.itla.consultoriomedico.gui;
import edu.itla.consultoriomedico.business.entity.Cita;
import edu.itla.consultoriomedico.business.entity.Medico;
import edu.itla.consultoriomedico.business.entity.Paciente;
import edu.itla.consultoriomedico.business.entity.Reservacion;
import edu.itla.consultoriomedico.business.enums.RepositoryEnum;
import edu.itla.consultoriomedico.business.factory.ApplicationContext;
import edu.itla.consultoriomedico.business.repository.PacienteRepository;
import edu.itla.consultoriomedico.business.repository.ReservacionRepository;

import java.time.LocalDateTime;


public class Main {

    public static void main(String[] args){

        Paciente p = new Paciente();
        p.setCedula(12);
        p.setNombre("Jose");
        p.setApellido("Kaz");


        Cita c = new Cita();
        c.setDescripcion("Cita 1");
        c.setHorario(LocalDateTime.now());
        Cita c2 = new Cita();
        c2.setDescripcion("Cita2");
        c2.setHorario(LocalDateTime.now());

        Reservacion reservacion1 = new Reservacion();
        reservacion1.setPaciente(p);
        reservacion1.setCita(c);
        Reservacion reservacion2 = new Reservacion();
        reservacion2.setPaciente(p);
        reservacion2.setCita(c2);
       

        ReservacionRepository reservacionlist =(ReservacionRepository) ApplicationContext.getReposiory(RepositoryEnum.RESERVACION_REPOSITORY);
        reservacionlist.crear(reservacion1);
        reservacionlist.crear(reservacion2);

        reservacionlist.findAll()
                .stream()
                .filter(r -> r.getPaciente().getCedula() == 12)
                .forEach(x -> System.out.print(x.getPaciente().getCedula() + "-" + x.getCita()));

    }

}
