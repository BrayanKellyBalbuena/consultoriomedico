package edu.itla.consultoriomedico.business.entity;

public class Paciente extends Persona{
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    private Long codigo;
}
