package edu.itla.consultoriomedico.business.entity;

import java.util.List;

public class Medico extends Persona{
    private Long id;
    private List<EspecialidadMedica> especialidades;

    public Long getId() {
        return id;
    }

    public void setCodigo(Long codigo) {
        id = codigo;
    }

    public List<EspecialidadMedica> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<EspecialidadMedica> especialidades) {
        this.especialidades = especialidades;
    }
}
