package edu.itla.consultoriomedico.business.entity;

import java.io.Serializable;
import java.util.Date;

public class Paciente extends Persona {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paciente(long id,String nombre, String apellido, int telefono) {
        super(nombre, apellido, telefono);
        this.id = id;
    }

    public Paciente(String nombre, String apellido, int telefono, Date fechaNacimiento, String direccion, int id) {
        super(nombre, apellido, telefono, fechaNacimiento, direccion);
        this.id = id;
    }
}
