package edu.itla.consultoriomedico.business.entity;

public class Paciente extends Persona{
    private int Cedula;

    public int getCedula() {
        return Cedula;
    }

    public void setCedula(int cedula) {
        Cedula = cedula;
    }
}
