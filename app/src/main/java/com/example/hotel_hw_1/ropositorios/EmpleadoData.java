/**
 * Autor: K. Jabier O'Reilly
 * Proyecto: Gestión de Hotel - Práctica 1ª Evaluación (PMDM 2025/2026)
 * Clase: EmpleadoData.java
 * Descripción: Clase encargada de gestionar los datos de los empleados del hotel.
 *              Proporciona métodos para agregar, eliminar, actualizar y consultar
 *              empleados, así como para obtener listados filtrados por rol.
 * Centro: C.F.G.S. Desarrollo de Aplicaciones Multiplataforma
 * Módulo: Programación Multimedia y Dispositivos Móviles
 */

package com.example.hotel_hw_1.ropositorios;

import com.example.hotel_hw_1.model.Empleado;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoData {

    private static final List<Empleado> listaEmpleados = new ArrayList<>();

    static {
        // Lista inicial (incluyendo los que también existen en UsuarioData)
        listaEmpleados.add(new Empleado("Julia", "Pérez", "Recepción", "recepcion@hotel.com", "600123456"));
        listaEmpleados.add(new Empleado("Raúl", "García", "Mantenimiento", "mmto@hotel.com", "600654321"));
        listaEmpleados.add(new Empleado("Marta", "López", "Limpieza", "limpieza@hotel.com", "600987654"));

        // Algunos nuevos para rellenar el listado
        listaEmpleados.add(new Empleado("Laura", "Ruiz", "Limpieza", "laura.ruiz@hotel.com", "611223344"));
        listaEmpleados.add(new Empleado("Carlos", "Torres", "Mantenimiento", "carlos.torres@hotel.com", "622334455"));
    }

    public static List<Empleado> getEmpleados() {
        return listaEmpleados;
    }

    public static void agregarEmpleado(Empleado e) {
        listaEmpleados.add(e);
    }

    public static void eliminarEmpleado(Empleado e) {
        listaEmpleados.remove(e);
    }

    public static void actualizarEmpleado(int index, Empleado nuevo) {
        listaEmpleados.set(index, nuevo);
    }

    public static Empleado getEmpleadoPorNombre(String nombre, String apellidos) {
        for (Empleado e : listaEmpleados) {
            if (e.getNombre().equalsIgnoreCase(nombre) && e.getApellidos().equalsIgnoreCase(apellidos)) {
                return e;
            }
        }
        return null;
    }

    /*
    * Con este metodo garatizo entregar una lista de nombres
    * segun el rol que ocupan
    * asi puedo asignar a quien sea segun su especialidad
    * */

    public static String[] getNombresPorRol(String rol) {
        String nombres_total = "";

        for (Empleado e : listaEmpleados) {
            if (e.getRol().equalsIgnoreCase(rol)) {
                nombres_total += e.getNombre() + " " + e.getApellidos() + ";";
            }
        }
        // Dividimos los nombres en un array
        String[] nombres = nombres_total.split(";");
        return nombres;
    }


}
