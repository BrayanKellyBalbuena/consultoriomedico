package edu.itla.consultoriomedico.business.entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Medico extends Persona{


    public void setEspecialidades(Set<EspecialidadMedica> especialidades) {
        this.especialidades = especialidades;
    }

    @Transient
    private Set<EspecialidadMedica> especialidades = new HashSet<>();

    public Set<EspecialidadMedica> getEspecialidades() {
        return especialidades;
    }


    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Medico that = (Medico) obj;

        if (getId() != null ? !getId().equals(that.getId()) :
                that.getId() != null)
            return false;

        return true;
    }

    public int hashCode() {
        return (getId() != null ? getId().hashCode() : 0);
    }
}
