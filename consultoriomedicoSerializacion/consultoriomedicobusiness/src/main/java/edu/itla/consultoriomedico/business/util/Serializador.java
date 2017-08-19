package edu.itla.consultoriomedico.business.util;

import edu.itla.consultoriomedico.business.entity.Paciente;
import edu.itla.consultoriomedico.business.enums.PathEnums;
import edu.itla.consultoriomedico.business.enums.SerializadorEnum;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Serializador<T> {

    private String ruta;

    public Serializador(SerializadorEnum sEnum) {
        switch (sEnum) {
            case PACIENTES:
                ruta = PropertyPath.getProperties().get(PathEnums.DATA_PACIENTES);
                System.out.println(ruta);
                break;
            case MEDICOS:
                 ruta = PropertyPath.getProperties().get(PathEnums.DATA_MEDICOS) ;
                break;
            default:
                ruta = null;
        }
    }

    public void escribirObjecto(List<Paciente> datos ) throws FileNotFoundException, IOException {

        FileOutputStream fos = new FileOutputStream(ruta);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject( datos);
        oos.close();
    }

    public  List<T> leerObjeto() throws FileNotFoundException, IOException, ClassNotFoundException{
        ArrayList<T> arraylist = new ArrayList<T>();
        FileInputStream fis = new FileInputStream(ruta);
        ObjectInputStream ois = new ObjectInputStream(fis);
        arraylist = (ArrayList<T>) ois.readObject();
        ois.close();
        fis.close();
        return arraylist;
    }

}
