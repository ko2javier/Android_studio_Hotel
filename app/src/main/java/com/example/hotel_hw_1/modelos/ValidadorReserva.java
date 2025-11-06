/**
 * Autor: K. Jabier O'Reilly
 *
 */
package com.example.hotel_hw_1.modelos;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/*
* Con esta clase validamos todos los campos
* de reserva que son muchisimos
* como es algo tan particular lo hacemos de una sola vez
* */
public class ValidadorReserva {

    public static boolean validarFormulario(
            Context context,
            View v,
            RadioGroup radioGroupHabitacion,
            RadioButton rbSimple,
            RadioButton rbDoble,
            RadioButton rbTriple,
            EditText editFecha,
            EditText etNombre,
            EditText etApellidos) {

        int errores = 0;
        StringBuilder mensajes = new StringBuilder();

        // Paso 1: Validar tipo de habitación
        int idSeleccionado = radioGroupHabitacion.getCheckedRadioButtonId();
        if (idSeleccionado == -1) {
            errores++;
            mensajes.append("• Debe seleccionar un tipo de habitación.\n");
            rbSimple.setTextColor(Color.RED);
            rbDoble.setTextColor(Color.RED);
            rbTriple.setTextColor(Color.RED);
        } else {
            rbSimple.setTextColor(Color.BLACK);
            rbDoble.setTextColor(Color.BLACK);
            rbTriple.setTextColor(Color.BLACK);
        }

        // Paso 2: Validar fecha
        String fecha = editFecha.getText().toString().trim();
        if (fecha.isEmpty()) {
            errores++;
            mensajes.append("• El campo fecha no puede estar vacío.\n");
            editFecha.setError("Campo obligatorio");
        } else {
            editFecha.setError(null);
        }

        // Paso 3: Validar nombre y apellidos (solo para recepcionista)
        if (Usuario.getInstance().getTipo_usuario().equalsIgnoreCase("recepcionista")) {

            String nombre = etNombre.getText().toString().trim();
            String apellidos = etApellidos.getText().toString().trim();
            String patron = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]{3,}$";

            boolean nombreValido = nombre.matches(patron);
            boolean apellidosValidos = apellidos.matches(patron);

            if (!nombreValido) {
                errores++;
                mensajes.append("• Nombre inválido.\n");
                etNombre.setError("Nombre inválido (mínimo 3 letras)");
            } else {
                etNombre.setError(null);
            }

            if (!apellidosValidos) {
                errores++;
                mensajes.append("• Apellidos inválidos.\n");
                etApellidos.setError("Apellidos inválidos (mínimo 3 letras)");
            } else {
                etApellidos.setError(null);
            }
        }

        // Paso 4: Mostrar errores si existen usando el metodp de clase Validaciones
        if (errores > 0 ) {
            Validaciones.mostrarErrores(context, mensajes);
            return false;
        }

        return true;
    }
}
