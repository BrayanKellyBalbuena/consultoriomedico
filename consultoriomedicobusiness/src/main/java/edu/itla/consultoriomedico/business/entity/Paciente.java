package edu.itla.consultoriomedico.business.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Paciente extends Persona implements Serializable {

    @OneToMany(mappedBy = "paciente")
    Set<Cita> Citas = new HashSet<>();

    public Set<Cita> getCitas() {
        return Citas;
    }

    public void setCitas(Set<Cita> citas) {
        Citas = citas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Paciente that = (Paciente) o;

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
