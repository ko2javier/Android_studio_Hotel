/**
 * Autor: K. Jabier O'Reilly
 * Proyecto: Gestión de Hotel - Práctica 1ª Evaluación (PMDM 2025/2026)
 * Clase: UsuarioData.java

 */

package com.example.hotel_hw_1.repositorio;

import com.example.hotel_hw_1.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioData {
    private static List<Usuario> usuarios = new ArrayList<>();

    static {
        usuarios.add(new Usuario("gerente@hotel.com", "1234", "gerente",
                "Pedrito", "Calvo", "666777888"));
        usuarios.add(new Usuario("recepcion@hotel.com", "1234", "recepcionista",
                "Ana", "Martínez", "666777888"));
        usuarios.add(new Usuario("limpieza@hotel.com", "1234", "limpieza",
                "Luis", "Pérez", "666111222"));
        usuarios.add(new Usuario("mantenimiento@hotel.com", "1234", "mantenimiento",
                "Marcos", "Gómez", "666333444"));
        usuarios.add(new Usuario("huesped_2@hotel.com", "1234", "huesped",
                "Juan", "Lorenzo", "699999999"));
        usuarios.add(new Usuario("huesped@hotel.com", "1234", "huesped",
                "Diana", "Rio", "699999999"));
    }

    public static Usuario checkLogin(String email, String pass) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email) && u.getPass().equals(pass)) {
                return u;
            }
        }
        return null;
    }

    public static void addUsuario(Usuario u) {
        usuarios.add(u);
    }
}
