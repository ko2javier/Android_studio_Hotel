package com.example.hotel_hw_1.model;

public class Planta {

    private String nombre;
    private int libres;
    private String simples;
    private String dobles;
    private String triples;

    private int numSimples;
    private int numDobles;
    private int numTriples;

    public Planta(String nombre, int libres, String simples, String dobles, String triples) {
        this.nombre = nombre;
        this.libres = libres;
        this.simples = simples;
        this.dobles = dobles;
        this.triples = triples;

        this.numSimples = simples.split(",").length;
        this.numDobles = dobles.split(",").length;
        this.numTriples = triples.split(",").length;
    }

    public String getNombre() { return nombre; }

    public int getLibres() { return libres; }

    public int getNumSimples() { return numSimples; }
    public int getNumDobles() { return numDobles; }
    public int getNumTriples() { return numTriples; }

    // 🔹 Resta una habitación del tipo que se reserva
    public boolean reservar(String tipoHabitacion) {
        switch (tipoHabitacion.toLowerCase()) {
            case "simple":
                if (numSimples > 0) {
                    numSimples--;
                    libres--;
                    return true;
                }
                break;
            case "doble":
                if (numDobles > 0) {
                    numDobles--;
                    libres--;
                    return true;
                }
                break;
            case "triple":
                if (numTriples > 0) {
                    numTriples--;
                    libres--;
                    return true;
                }
                break;
        }
        return false;
    }
}
