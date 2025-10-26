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
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotel_hw_1.R;
import com.example.hotel_hw_1.model.AdaptadorEncuestaEditable;
import com.example.hotel_hw_1.model.Encuesta;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class Crear_Encuesta_Satisfaccion extends AppCompatActivity {
    // definimos variables
    private ListView lvEncuestas;
    private AdaptadorEncuestaEditable adaptador;
    private Button btnEnviar, btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_encuesta_satisfaccion);

        // Referencio las variables con ID
        lvEncuestas = findViewById(R.id.lv_encuestas);
        btnEnviar = findViewById(R.id.btn_enviar_encuesta);
        btnVolver = findViewById(R.id.btn_volver_encuesta);

        // Creamos la lista y añadimos registros
        List<Encuesta> listaPreguntas = new ArrayList<>();
        listaPreguntas.add(new Encuesta("Limpieza de la habitación", 1, 0, true));
        listaPreguntas.add(new Encuesta("Limpieza de la planta", 1, 0, true));
        listaPreguntas.add(new Encuesta("Limpieza del vestíbulo", 1, 0, true));
        listaPreguntas.add(new Encuesta("Atención del personal de limpieza", 1, 0, true));
        listaPreguntas.add(new Encuesta("Atención del personal de mantenimiento", 1, 0, true));
        listaPreguntas.add(new Encuesta("Atención en recepción", 1, 0, true));
        listaPreguntas.add(new Encuesta("Servicio de mantenimiento durante su estancia", 1, 0, true));

        // Configuramos adaptador con la lista creada
        adaptador = new AdaptadorEncuestaEditable(this, listaPreguntas);
        lvEncuestas.setAdapter(adaptador);

        // Botón Enviar
        btnEnviar.setOnClickListener(v -> {
            mostrar_resultados_encuesta(v);
        });

        // Botón Volver
        btnVolver.setOnClickListener(v -> finish());
    }

    private void mostrar_resultados_encuesta(View v) {
        List<Encuesta> resultados = adaptador.getResultados();

        float suma = 0;
        for (Encuesta e : resultados) {
            suma += e.getPromedio();
        }
        float promedioGeneral = suma / resultados.size();

        new AlertDialog.Builder(this)
                .setTitle("Encuesta enviada")
                .setMessage("Gracias por su valoración.\n\nPromedio general: "
                        + String.format("%.1f", promedioGeneral) + " ★")
                .setPositiveButton("Aceptar", (dialog, which) -> {
                    Snackbar.make(v, "Encuesta registrada correctamente", Snackbar.LENGTH_LONG).show();

                })
                .show();
        v.postDelayed(this::finish, 2300);// damos unos segundos y volvemos a la pantalla inicial

    }
}
