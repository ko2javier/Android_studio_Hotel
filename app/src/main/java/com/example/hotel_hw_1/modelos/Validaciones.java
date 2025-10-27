/**
 * Autor: K. Jabier O'Reilly
 * Proyecto: Gestión de Hotel - Práctica 1ª Evaluación (PMDM 2025/2026)
 * Clase: Validaciones.java
 * Descripción: Clase utilitaria que centraliza las validaciones de los campos
 *              de entrada del sistema.
 * Centro: C.F.G.S. Desarrollo de Aplicaciones Multiplataforma
 * Módulo: Programación Multimedia y Dispositivos Móviles
 */

package com.example.hotel_hw_1.modelos;

import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.*;

import com.google.android.material.snackbar.Snackbar;

public class Validaciones {

    //  Valida campo vacío
    public static boolean validarCampoNoVacio(View v, EditText campo, String mensaje) {
        String texto = campo.getText().toString().trim();
        if (texto.isEmpty()) {
            Snackbar.make(v, mensaje, Snackbar.LENGTH_SHORT).show();
            campo.requestFocus();
            return false;
        }
        return true;
    }

    //  Nombre o apellidos (solo letras y mínimo 3 caracteres)
    public static boolean validarNombreOApellido(View v, EditText campo, String etiqueta) {
        String texto = campo.getText().toString().trim();
        if (!texto.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]{3,}$")) {
            Snackbar.make(v, etiqueta + " debe tener al menos 3 letras válidas", Snackbar.LENGTH_SHORT).show();
            campo.requestFocus();
            return false;
        }
        return true;
    }

    //  Teléfono: 9 dígitos
    public static boolean validarTelefono(View v, EditText campo) {
        String texto = campo.getText().toString().trim();
        if (!texto.matches("^\\d{9}$")) {
            Snackbar.make(v, "El teléfono debe tener 9 dígitos numéricos", Snackbar.LENGTH_SHORT).show();
            campo.requestFocus();
            return false;
        }
        return true;
    }

    //  Contraseña: mínimo 4 caracteres
    public static boolean validarPassword(View v, EditText campo) {
        String pass = campo.getText().toString().trim();
        if (pass.length() < 4) {
            Snackbar.make(v, "La contraseña debe tener al menos 4 caracteres", Snackbar.LENGTH_SHORT).show();
            campo.requestFocus();
            return false;
        }
        return true;
    }

    //  Confirmar contraseñas iguales!!
    public static boolean validarConfirmacionPassword(View v, EditText pass1, EditText pass2) {
        if (!pass1.getText().toString().equals(pass2.getText().toString())) {
            Snackbar.make(v, "Las contraseñas no coinciden", Snackbar.LENGTH_SHORT).show();
            pass2.requestFocus();
            return false;
        }
        return true;
    }
    //  Confirmar email
    public static boolean validarEmail(View v, EditText campo) {
        String email = campo.getText().toString().trim();

        // Expresión regular para validar formato de email
        String patronEmail = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

        if (!email.matches(patronEmail) || email.isEmpty()) {
            Snackbar.make(v, "Email vacio o formato inválido. Corrija !", Snackbar.LENGTH_SHORT).show();
            campo.requestFocus();
            return false;
        }

        return true;
    }


    // Método para validar numero de habitaciones
    public static boolean validarHabitacionObligatoria(View v,
                                                       EditText etxNumero,
                                                       TextView txtError) {
        String valor = etxNumero.getText().toString().trim();

        // paso 1 ver si esta vacio el campo. Si lo esta error !!
        if (valor.isEmpty()) {
            txtError.setText("El número de habitación es obligatorio");
            txtError.setVisibility(View.VISIBLE);
            etxNumero.setError("Obligatorio");
            return false;
        }
 // Paso 2 valido el numero escrito con parse En caso negativo error!!
        int n;
        try {
            n = Integer.parseInt(valor);
        } catch (NumberFormatException e) {
            txtError.setText("Debe ser un número válido");
            txtError.setVisibility(View.VISIBLE);
            etxNumero.setError("Número inválido");
            return false;
        }
// Paso 3 valido el rango del la habitacion si es incorrecto error !!
        if (n < 100 || n > 599) {
            txtError.setText("El número debe estar entre 100 y 599");
            txtError.setVisibility(View.VISIBLE);
            etxNumero.setError("Fuera de rango");
            return false;
        }

        // OK
        txtError.setVisibility(View.GONE);
        etxNumero.setError(null);
        return true;
    }



    // Método para validar nombre y apellidos

    public static boolean validarNombreYApellidos(View v, EditText nombre, EditText apellidos) {
        String patron = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]{3,}$";
        String nom = nombre.getText().toString().trim();
        String ape = apellidos.getText().toString().trim();

        boolean respuesta = false;

        // Validar nombre
        if (!nom.matches(patron)) {
            Snackbar.make(v, "El nombre debe tener al menos 3 letras válidas", Snackbar.LENGTH_SHORT).show();
            nombre.setBackgroundColor(Color.parseColor("#FFCDD2"));
            respuesta = true;
        } else {
            nombre.setBackgroundColor(Color.TRANSPARENT);
        }

        // Validar apellido
        if (!ape.matches(patron)) {
            Snackbar.make(v, "El apellido debe tener al menos 3 letras válidas", Snackbar.LENGTH_SHORT).show();
            apellidos.setBackgroundColor(Color.parseColor("#FFCDD2"));
            respuesta = true;
        } else {
            apellidos.setBackgroundColor(Color.TRANSPARENT);
        }

        // Si no hubo errores, respuesta seguirá en false (válido)
        return !respuesta;
    }


}
