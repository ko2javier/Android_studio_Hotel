/**
 * Autor: K. Jabier O'Reilly
 * Proyecto: Gestión de Hotel - Práctica 1ª Evaluación (PMDM 2025/2026)
 * Clase: Huesped.java
 * Descripción: Clase modelo que representa a un huésped del hotel,
 *              incluyendo sus datos personales, habitación asignada
 *              y estado actual (Check-In o Check-Out).
 * Centro: C.F.G.S. Desarrollo de Aplicaciones Multiplataforma
 * Módulo: Programación Multimedia y Dispositivos Móviles
 */

package com.example.hotel_hw_1.modelos;

public class Huesped {
    private String nombre;
    private String apellidos;
    private String telefono;
    private String habitacion;
    private boolean checkInActivo; // true = hospedado, false = check-out

    public Huesped(String nombre, String apellidos, String telefono, String habitacion) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.habitacion = habitacion;
        this.checkInActivo = true;
    }

    // Getters y setters
    public String getNombre() { return nombre; }
    public String getApellidos() { return apellidos; }
    public String getTelefono() { return telefono; }
    public String getHabitacion() { return habitacion; }
    public boolean isCheckInActivo() { return checkInActivo; }

    public void setCheckInActivo(boolean activo) { this.checkInActivo = activo; }

    public String getEstado() {
        return checkInActivo ? "Hospedado" : "Check-Out";
    }
}

