package edu.itla.consultoriomedico.business.entity;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Paciente extends Persona implements Serializable {

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
