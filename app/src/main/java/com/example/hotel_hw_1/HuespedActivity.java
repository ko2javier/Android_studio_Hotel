
/**
 * Autor: K. Jabier O'Reilly
 * Proyecto: Gestión de Hotel - Práctica 1ª Evaluación (PMDM 2025/2026)
 * Clase: HuespedActivity.java
 * Descripción: Menú principal del huésped con acceso a sus reservas, encuestas
 *              y opciones de solicitud de limpieza o edición de perfil.
 * Centro: C.F.G.S. Desarrollo de Aplicaciones Multiplataforma
 * Módulo: Programación Multimedia y Dispositivos Móviles
 */

package com.example.hotel_hw_1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotel_hw_1.model.Usuario;
import com.google.android.material.snackbar.Snackbar;

import java.util.Stack;

public class HuespedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huesped_menu);

        // Defino botones

        Button btn_consultar_perfil= findViewById(R.id.btn_consultar_editar_perfil_huesped);

        Button btn_add_reservas = findViewById(R.id.btn_add_reservas_hotel_huesped);
        Button btn_check_reservas_done= findViewById(R.id.btn_check_status_realizadas);
        Button btn_realizar_encuestas= findViewById(R.id.btn_realizar_encuestas_al_hotel);
        Button boton_cerrar_sesion= findViewById(R.id.boton_cerrar_sesion);
        Button btn_solictar_limpieza= findViewById(R.id.btn_solictar_limpieza);

        // Pongo a la escucha botones
        btn_consultar_perfil.setOnClickListener(v -> {
            Intent i= new Intent(HuespedActivity.this, Consultar_Editar_Perfil.class);
            startActivity(i);

        });
        btn_realizar_encuestas.setOnClickListener(v->{
            Intent i = new Intent(HuespedActivity.this, Crear_Encuesta_Satisfaccion.class);
            startActivity(i);
        });

        btn_add_reservas.setOnClickListener(v->{
            Intent i= new Intent(HuespedActivity.this, Realizar_Reserva_Activity.class);
            startActivity(i);
        });

        btn_check_reservas_done.setOnClickListener(v->{
            Intent i = new Intent(HuespedActivity.this, Consultar_Estado_Reservas.class);
            startActivity(i);
        });

        boton_cerrar_sesion.setOnClickListener(v->{
            Intent i = new Intent(HuespedActivity.this, Pantalla_Inicio.class);
            Usuario.setInstance(null); // limpio lo que tenga el usuario

            Snackbar.make(v, "Sesión cerrada correctamente", Snackbar.LENGTH_SHORT).show();
            // limpio lo que exista en el historial de las activities, para q cdo comience este todo en cero!!
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);

        });
        btn_solictar_limpieza.setOnClickListener(v->{
            Intent i = new Intent(HuespedActivity.this,Solicitar_Tarea.class);
            startActivity(i);
        });


    }
}