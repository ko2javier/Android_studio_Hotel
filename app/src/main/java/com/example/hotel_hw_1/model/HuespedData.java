package com.example.hotel_hw_1.model;
/**
 * Autor: K. Jabier O'Reilly
 * Proyecto: Gestión de Hotel - Práctica 1ª Evaluación (PMDM 2025/2026)
 * Clase: HuespedData.java
 * Descripción: Clase encargada de gestionar la información de los huéspedes,
 *              incluyendo el registro, búsqueda y actualización del estado
 *              (Check-In o Check-Out) dentro del sistema del hotel.
 * Centro: C.F.G.S. Desarrollo de Aplicaciones Multiplataforma
 * Módulo: Programación Multimedia y Dispositivos Móviles
 */

import java.util.ArrayList;
import java.util.List;

public class HuespedData {
    private static final List<Huesped> listaHuespedes = new ArrayList<>();
// agrego una lista de varios usuarios
    static {

        listaHuespedes.add(new Huesped("Diana", "Rio", "699999999", "204"));
        listaHuespedes.add(new Huesped("Carlos", "Perez", "688888888", "105"));
        listaHuespedes.add(new Huesped("Lucia", "Martinez", "677777777", "306"));
        listaHuespedes.add(new Huesped("Javier", "Lopez", "666555444", "207"));
        listaHuespedes.add(new Huesped("Ana", "Torres", "699111222", "410"));
        listaHuespedes.add(new Huesped("Miguel", "Sanchez", "612345678", "512"));
        listaHuespedes.add(new Huesped("Laura", "Diaz", "698745632", "302"));
        listaHuespedes.add(new Huesped("Elena", "Garcia", "671234567", "215"));



    }

    public static void agregarHuesped(Huesped h) {
        listaHuespedes.add(h);
    }

    public static Huesped buscarHuesped(String nombre, String apellidos) {
        for (Huesped h : listaHuespedes) {
            if (h.getNombre().equalsIgnoreCase(nombre) &&
                    h.getApellidos().equalsIgnoreCase(apellidos)) {
                return h;
            }
        }
        return null;
    }
// Ponemos el check out en el usuario para que se vea en la lista!!
    public static boolean marcarCheckOut(String nombre, String apellidos) {
        Huesped h = buscarHuesped(nombre, apellidos);
        if (h != null && h.isCheckInActivo()) {
            h.setCheckInActivo(false);
            return true;
        }
        return false;
    }

    public static List<Huesped> listarHuespedes() {
        return listaHuespedes;
    }
}

