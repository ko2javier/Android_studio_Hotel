package com.example.hotel_hw_1.model;

import java.util.ArrayList;
import java.util.List;

public class ReservaData {

    // 🔹 Lista de plantas (las mismas que se muestran en la pantalla de disponibilidad)
    private static final List<Planta> plantas = new ArrayList<>();

    static {
        plantas.add(new Planta("Planta 1", 9, "101, 102, 104", "115, 118, 120, 121", "198, 199"));
        plantas.add(new Planta("Planta 2", 8, "202, 204, 209", "220, 222, 228", "297, 299"));
        plantas.add(new Planta("Planta 3", 8, "303, 305, 310", "324, 325, 330", "397, 399"));
        plantas.add(new Planta("Planta 4", 10, "401, 403, 404, 408", "420, 425, 430", "496, 498, 499"));
        plantas.add(new Planta("Planta 5", 9, "501, 504, 507", "520, 525, 528, 530", "596, 598"));
    }

    // 🔹 Método principal para reservar una habitación según tipo
    public static boolean reservar(String tipoHabitacion) {
        for (Planta p : plantas) {
            boolean reservada = p.reservar(tipoHabitacion);
            if (reservada) return true;
        }
        return false;
    }

    // 🔹 Mostrar disponibilidad total global
    public static String mostrarDisponibilidad() {
        int totalSimples = 0;
        int totalDobles = 0;
        int totalTriples = 0;

        for (Planta p : plantas) {
            totalSimples += p.getNumSimples();
            totalDobles += p.getNumDobles();
            totalTriples += p.getNumTriples();
        }

        return "- Simples: " + totalSimples +
                "  - Dobles: " + totalDobles +
                "  - Triples: " + totalTriples;
    }

    // 🔹 Obtener lista completa (para mostrar en listview si hiciera falta)
    public static List<Planta> getPlantas() {
        return plantas;
    }
}
