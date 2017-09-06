package edu.itla.consultoriomedico.gui;


import edu.itla.consultoriomedico.business.enums.ServiceEnum;
import edu.itla.consultoriomedico.business.services.PacienteService;
import edu.itla.consultoriomedico.business.services.impl.PacienteServiceImpl;
import edu.itla.consultoriomedico.business.util.DateUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import edu.itla.consultoriomedico.business.entity.Paciente;

public class Main {

    public static void main(String[] args){

        ApplicationContext context =
                new ClassPathXmlApplicationContext("/spring/applicationContext.xml");

        PacienteService pacienteService = (PacienteServiceImpl)
                context.getBean(ServiceEnum.PACIENTE_SERVICE.getValue());

//      Paciente paciente = new Paciente() ;
//        paciente.setNombre("Brayan");
//        paciente.setApellido("Kelly");
//        paciente.setTelefono(809760821);
//        paciente.setCorreo("b@b.com");
//        paciente.setFechaNacimiento(DateUtil.stringToDate("20/12/1995"));
//
//        pacienteService.save(paciente);
//        System.out.println("Guardado");
        //pacienteService.delete(1L);
        System.out.println(pacienteService.findAll().get(0).getNombre());


    }

}
