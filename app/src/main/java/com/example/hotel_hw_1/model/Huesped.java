package com.example.hotel_hw_1.model;

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

