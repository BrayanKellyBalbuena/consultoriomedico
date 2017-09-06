package edu.itla.consultoriomedico.business.entity;

public class ProcedimientoMedico extends Catalogo {

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        ProcedimientoMedico that = (ProcedimientoMedico) obj;

        if (getId() != null ? !getId().equals(that.getId()) :
                that.getId() != null)
            return false;

        return true;
    }

    public int hashCode() {
        return (getId() != null ? getId().hashCode() : 0);
    }
}

