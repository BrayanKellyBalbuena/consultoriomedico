package edu.itla.consultoriomedico.business.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Medico extends Persona{


    @ManyToOne
    @JoinColumn(name = "especialidad_id")
    private EspecialidadMedica especialidad;

    @Transient
    private Set<EspecialidadMedica> especialidades = new HashSet<>();

    public Set<EspecialidadMedica> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(Set<EspecialidadMedica> especialidades) {
        this.especialidades = especialidades;
    }

    public EspecialidadMedica getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(EspecialidadMedica especialidad) {
        this.especialidad = especialidad;
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
