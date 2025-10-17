package com.example.hotel_hw_1.model;

import java.util.ArrayList;
import java.util.List;

public class TareaData {

    private static List<Tarea> tareas = new ArrayList<>();

    // Añadir nueva tarea desde un huésped
    public static void agregarTarea(Tarea t) {
        tareas.add(t);
    }

    // Devolver todas las tareas
    public static List<Tarea> getTareas() {
        return tareas;
    }

    // Filtrar por tipo
    public static List<Tarea> getTareasPorTipo(String tipo) {
        List<Tarea> lista = new ArrayList<>();
        for (Tarea t : tareas) {
            if (t.getTipoTarea().equalsIgnoreCase(tipo)) {
                lista.add(t);
            }
        }
        return lista;
    }
}
