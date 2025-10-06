package com.example.hotel_hw_1.model;


import java.util.ArrayList;
import java.util.List;

public class UsuarioData {
    private static List<Usuario> usuarios = new ArrayList<>();

    static {
        usuarios.add(new Empleado("recepcion@hotel.com", "1234", "empleado",
                "Ana", "666777888", "Martínez", "recepcionista"));

        usuarios.add(new Empleado("limpieza@hotel.com", "1234", "empleado",
                "Luis", "666111222", "Pérez", "limpiador"));

        usuarios.add(new Empleado("mantenimiento@hotel.com", "1234", "empleado",
                "Pedro", "666333444", "Gómez", "mantenimiento"));

        usuarios.add(new Gest("huesped@hotel.com", "1234", "huesped",
                "López", "Carlos", "699999999"));

        usuarios.add(new Usuario("gerente@hotel.com", "1234", "gerente"));
    }
// Metodo para chear si un usuario esta en la lista
    public static Usuario checkLogin(String email, String pass) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email) && u.getPass().equals(pass)) {
                return u;
            }
        }
        return null;
    }
// Metodo para añadir un huesped
    public static void addUsuario(Usuario u) {
        usuarios.add(u);
    }
}
