package edu.itla.consultoriomedico.business.entity;

import java.time.LocalDate;
import java.util.Date;

public abstract class Persona {
    protected Long id;
    protected String nombre;
    protected String apellido;
    protected String correo;

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    protected int telefono;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    protected LocalDate fechaNacimiento;

    public int getTelefono() {
        return telefono;
    }
}
