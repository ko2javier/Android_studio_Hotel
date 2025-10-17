package com.example.hotel_hw_1.model;

public class Tarea {
    private String tipoTarea;
    private String planta;
    private String habitacion;
    private String zona;
    private String pasillo;
    private String estado;
    private String asignadaA;

    public Tarea(String tipoTarea, String planta, String habitacion, String zona, String pasillo) {
        this.tipoTarea = tipoTarea;
        this.planta = planta;
        this.habitacion = habitacion;
        this.zona = zona;
        this.pasillo = pasillo;
        this.estado = "Pendiente";
        this.asignadaA = "Sin asignar";
    }

    // Getters y Setters
    public String getTipoTarea() { return tipoTarea; }
    public String getPlanta() { return planta; }
    public String getHabitacion() { return habitacion; }
    public String getZona() { return zona; }
    public String getPasillo() { return pasillo; }
    public String getEstado() { return estado; }
    public String getAsignadaA() { return asignadaA; }

    public void setEstado(String estado) { this.estado = estado; }
    public void setAsignadaA(String asignadaA) { this.asignadaA = asignadaA; }
}
