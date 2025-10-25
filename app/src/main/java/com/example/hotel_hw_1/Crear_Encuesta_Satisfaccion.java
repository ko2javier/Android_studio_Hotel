/**
 * Autor: K. Jabier O'Reilly
 * Proyecto: Gestión de Hotel - Práctica 1ª Evaluación (PMDM 2025/2026)
 * Clase: Crear_Encuesta_Satisfaccion.java
 * Descripción: Permite al huésped valorar distintos aspectos del hotel y enviar su encuesta
 *              mostrando una confirmación con el promedio de puntuación.
 * Centro: C.F.G.S. Desarrollo de Aplicaciones Multiplataforma
 * Módulo: Programación Multimedia y Dispositivos Móviles
 */

package com.example.hotel_hw_1;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotel_hw_1.R;

public class Crear_Encuesta_Satisfaccion extends AppCompatActivity {

    private RatingBar rb_limpieza_habitacion, rb_limpieza_planta, rb_limpieza_vestibulo,
            rb_personal_limpieza, rb_personal_mantenimiento, rb_recepcion, rb_servicio_mantenimiento;
    private EditText et_comentario;
    private Button btn_enviar, btn_volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_encuesta_satisfaccion);

        // Vincular elementos
        rb_limpieza_habitacion = findViewById(R.id.rb_limpieza_habitacion);
        rb_limpieza_planta = findViewById(R.id.rb_limpieza_planta);
        rb_limpieza_vestibulo = findViewById(R.id.rb_limpieza_vestibulo);
        rb_personal_limpieza = findViewById(R.id.rb_personal_limpieza);
        rb_personal_mantenimiento = findViewById(R.id.rb_personal_mantenimiento);
        rb_recepcion = findViewById(R.id.rb_recepcion);
        rb_servicio_mantenimiento = findViewById(R.id.rb_servicio_mantenimiento);
        et_comentario = findViewById(R.id.et_comentario);
        btn_enviar = findViewById(R.id.btn_enviar_encuesta);
        btn_volver = findViewById(R.id.btn_volver_encuesta);

        // Acción del botón Enviar
        btn_enviar.setOnClickListener(v -> enviarEncuesta());

        // Acción del botón Volver
        btn_volver.setOnClickListener(v -> finish());
    }
// metodo para enviar la encuesta,
private void enviarEncuesta() {
    // Recogemos valores
    float r1 = rb_limpieza_habitacion.getRating();
    float r2 = rb_limpieza_planta.getRating();
    float r3 = rb_limpieza_vestibulo.getRating();
    float r4 = rb_personal_limpieza.getRating();
    float r5 = rb_personal_mantenimiento.getRating();
    float r6 = rb_recepcion.getRating();
    float r7 = rb_servicio_mantenimiento.getRating();

    // Calculamos promedio
    float promedio = (r1 + r2 + r3 + r4 + r5 + r6 + r7) / 7;
    String comentario = et_comentario.getText().toString().trim();

    // Mostramos simulación de envío
    new AlertDialog.Builder(this)
            .setTitle("Encuesta enviada")
            .setMessage("Gracias por su valoración.\n\nPromedio general: " +
                    String.format("%.1f", promedio) + " ⭐" +
                    (comentario.isEmpty() ? "" : "\n\nComentario:\n" + comentario))
            .setPositiveButton("Aceptar", (dialog, which) -> limpiarCampos())
            .show();
}

    // metodo para limpiar los valores de los ratings, seteamos a 1 valor x defecto!!
    private void limpiarCampos() {
        rb_limpieza_habitacion.setRating(1);
        rb_limpieza_planta.setRating(1);
        rb_limpieza_vestibulo.setRating(1);
        rb_personal_limpieza.setRating(1);
        rb_personal_mantenimiento.setRating(1);
        rb_recepcion.setRating(1);
        rb_servicio_mantenimiento.setRating(1);
        et_comentario.setText("");
    }
}
