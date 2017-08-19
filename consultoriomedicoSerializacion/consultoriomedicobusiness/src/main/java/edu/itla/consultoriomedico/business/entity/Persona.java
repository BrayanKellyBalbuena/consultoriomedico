package edu.itla.consultoriomedico.business.entity;

import java.io.Serializable;
import java.util.Date;

public abstract class Persona implements Serializable{
    protected String nombre;
    protected String apellido;
    protected int telefono;
    protected Date fechaNacimiento;
    protected String direccion;

    public Persona(String nombre, String apellido, int telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }

    public Persona(String nombre, String apellido, int telefono, Date fechaNacimiento, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
    }

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

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
