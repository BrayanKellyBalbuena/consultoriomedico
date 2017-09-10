package edu.itla.consultoriomedico.business.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "especialidad_medica")
public class EspecialidadMedica extends Catalogo {

    @Transient
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
