package com.example.hotel_hw_1.model;

public class ReservaData {

    private static int simples = 16;
    private static int dobles = 17;
    private static int triples = 11;

    public static boolean reservar(String tipoHabitacion) {
        switch (tipoHabitacion) {
            case "Simple":
                if (simples > 0) { simples--; return true; }
                break;
            case "Doble":
                if (dobles > 0) { dobles--; return true; }
                break;
            case "Triple":
                if (triples > 0) { triples--; return true; }
                break;
        }
        return false;
    }

    public static String mostrarDisponibilidad() {
        return "- Simples: " + simples + "  - Dobles: " + dobles + "  - Triples: " + triples;
    }
}
