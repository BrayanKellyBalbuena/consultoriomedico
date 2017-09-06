package edu.itla.consultoriomedico.business.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class EspecialidadMedica extends Catalogo implements Serializable {
    Set<Medico> Medicos = new HashSet<>();

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        EspecialidadMedica that = (EspecialidadMedica) obj;

        if (getId() != null ? !getId().equals(that.getId()) :
                that.getId() != null)
            return false;

        return true;
    }

    public int hashCode() {
        return (getId() != null ? getId().hashCode() : 0);
    }
}
