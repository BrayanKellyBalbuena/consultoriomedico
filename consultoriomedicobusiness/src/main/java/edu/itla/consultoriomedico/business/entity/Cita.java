package edu.itla.consultoriomedico.business.entity;

import java.time.LocalDateTime;

public class Cita extends Catalogo {

   private Paciente paciente;
   private Medico medico;
   private ProcedimientoMedico procedimientoMedico;
    private LocalDateTime fecha;

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
        return fecha;
    }

    public void setHorario(LocalDateTime horario) {
        this.fecha = horario;
    }

    public ProcedimientoMedico getProcedimientoMedico() {
        return procedimientoMedico;
    }

    public void setProcedimientoMedico(ProcedimientoMedico procedimientoMedico) {
        this.procedimientoMedico = procedimientoMedico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Cita that = (Cita) o;

        if (getId() != null ? !getId().equals(that.getId())
                : that.getId() != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (getId() != null ? getId().hashCode() : 0);
    }
}
