package edu.itla.consultoriomedico.business.entity;

public class Paciente extends Persona{
    private int id;

    public Paciente() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
