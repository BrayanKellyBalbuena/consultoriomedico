package edu.itla.consultoriomedico.business.entity;

import java.time.LocalDateTime;

public class Cita {

    private Long CitaId;
   private Paciente paciente;
   private Medico medico;
   private ProcedimientoMedico procedimientoMedico;
   private LocalDateTime horario;
   private String descripcion;


    public Long getCitaId() {
        return CitaId;
    }

    public void setCitaId(Long citaId) {
        CitaId = citaId;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "["+ this.getDescripcion()+" "+ this.getHorario().toString()+"]";
    }

    public ProcedimientoMedico getProcedimientoMedico() {
        return procedimientoMedico;
    }

    public void setProcedimientoMedico(ProcedimientoMedico procedimientoMedico) {
        this.procedimientoMedico = procedimientoMedico;
    }
}
