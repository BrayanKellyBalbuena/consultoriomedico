package edu.itla.consultoriomedico.business.entity;

import java.util.List;

public class Medico extends Persona{

    public  Medico() {
        super();
    }

    private Long codigo;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }


}
