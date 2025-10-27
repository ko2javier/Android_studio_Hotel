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

        //  Paso 1 Validar tipo habitación
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

        //  PAso 2 Validar fecha
        String fecha = editFecha.getText().toString().trim();
        if (fecha.isEmpty()) {
            errores++;
            mensajes.append(" El campo fecha no puede estar vacío.\n");
            editFecha.setBackgroundColor(Color.parseColor("#FFCDD2"));
        } else {
            editFecha.setBackgroundColor(Color.TRANSPARENT);
        }

        //  PAso 3 Validar nombre/apellidos si es recepcionista
        if (Usuario.getInstance().getTipo_usuario().equalsIgnoreCase("recepcionista")) {
            boolean datosValidos = Validaciones.validarNombreYApellidos(v, etNombre, etApellidos);
            if (!datosValidos) {
                errores++;
                mensajes.append("Nombre o apellidos inválidos.\n");
            }
        }

        //  Paso 4 Mostrar errores
        if (errores > 0) {
            new AlertDialog.Builder(context)
                    .setTitle("Errores en el formulario")
                    .setMessage(mensajes.toString())
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton("Aceptar", null)
                    .show();
            return false;
        }

        return true;
    }
}
