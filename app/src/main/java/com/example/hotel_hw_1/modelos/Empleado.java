/**
 * Autor: K. Jabier O'Reilly
 * Proyecto: Gestión de Hotel - Práctica 1ª Evaluación (PMDM 2025/2026)
 * Clase: Empleado.java
 * Descripción: Clase modelo que representa a un empleado del hotel,
 *              incluyendo sus datos personales y su rol dentro del establecimiento.
 * Centro: C.F.G.S. Desarrollo de Aplicaciones Multiplataforma
 * Módulo: Programación Multimedia y Dispositivos Móviles
 */

package com.example.hotel_hw_1.modelos;

public class Empleado {

    private String nombre;
    private String apellidos;
    private String rol;       // Aca defino el tipo de empleado que sera!!!!
    private String email;
    private String telefono;

    public Empleado(String nombre, String apellidos, String rol, String email, String telefono) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.rol = rol;
        this.email = email;
        this.telefono = telefono;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getApellidos() { return apellidos; }
    public String getRol() { return rol; }
    public String getEmail() { return email; }
    public String getTelefono() { return telefono; }

    // Setters
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public void setRol(String rol) { this.rol = rol; }
    public void setEmail(String email) { this.email = email; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    @Override
    public String toString() {
        return nombre + " " + apellidos + " - " + rol;
    }
}

