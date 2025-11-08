/**
 * Autor: K. Jabier O'Reilly
 * Proyecto: Gestión de Hotel - Práctica 1ª Evaluación (PMDM 2025/2026)
 * Clase: Encuesta.java

 */

package com.example.hotel_hw_1.modelo;

public class Encuesta {
    private String categoria;
    private float promedio;
    private int cantidad;
    private boolean visible; // este parametro me permite completar la idea de mostrar encuestas segun rol!!

    public Encuesta(String categoria, float promedio, int cantidad, boolean visible) {
        this.categoria = categoria;
        this.promedio = promedio;
        this.cantidad = cantidad;
        this.visible = visible;
    }

    public String getCategoria() { return categoria; }
    public float getPromedio() { return promedio; }
    public void setPromedio(float promedio){
        this.promedio= promedio;
    }
    public int getCantidad() { return cantidad; }
    public boolean isVisible() { return visible; }

    public void setVisible(boolean visible) { this.visible = visible; }
}
