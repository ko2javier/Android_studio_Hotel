/**
 * Autor: K. Jabier O'Reilly
 * Proyecto: Gestión de Hotel - Práctica 1ª Evaluación (PMDM 2025/2026)
 * Clase: Validaciones.java
 * Descripción: Clase utilitaria que centraliza las validaciones de los campos
 *              de entrada del sistema.
 * Centro: C.F.G.S. Desarrollo de Aplicaciones Multiplataforma
 * Módulo: Programación Multimedia y Dispositivos Móviles
 */

package com.example.hotel_hw_1.model;

import android.view.View;
import android.widget.EditText;

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

}
