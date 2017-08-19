package edu.itla.consultoriomedico.business.entity;

import java.util.Date;

public class Reservacion {
    private Long Codigo;
    private Paciente paciente;
    private Cita cita;
    private Date fecha;

    public Long getCodigo() {
        return Codigo;
    }

    public void setCodigo(Long codigo) {
        Codigo = codigo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
