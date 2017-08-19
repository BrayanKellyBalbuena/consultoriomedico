package edu.itla.consultoriomedico.business.entity;

public class ProcedimientoMedico {
    private Long Id;
    private String descripcion;


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

