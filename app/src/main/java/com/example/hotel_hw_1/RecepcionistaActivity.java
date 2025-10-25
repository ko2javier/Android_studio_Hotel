/**
 * Autor: K. Jabier O'Reilly
 * Proyecto: Gestión de Hotel - Práctica 1ª Evaluación (PMDM 2025/2026)
 * Clase: RecepcionistaActivity.java
 * Descripción: Menú principal del recepcionista con acceso a reservas, huéspedes,
 *              check-in/out, encuestas y ocupación del hotel.
 * Centro: C.F.G.S. Desarrollo de Aplicaciones Multiplataforma
 * Módulo: Programación Multimedia y Dispositivos Móviles
 */

package com.example.hotel_hw_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotel_hw_1.model.Usuario;
import com.google.android.material.snackbar.Snackbar;

public class RecepcionistaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recepcionista_menu);

        // defino botones
        Button btn_consultar_editar_perfil_recp=
                findViewById(R.id.btn_consultar_editar_perfil_recp);
        Button btn_consultar_ocupacion_hotel_recp=
                findViewById(R.id.btn_consultar_ocupacion_hotel_recp);
        Button btn_consultar_listado_huspedes_recp =
                findViewById(R.id.btn_consultar_listado_huspedes_recp);
        Button btn_add_reservas_hotel= findViewById(R.id.btn_add_reservas_hotel);
        Button btn_gestionar_check_in_out= findViewById(R.id.btn_gestionar_check_in_out);
        Button btn_consultar_encuestas= findViewById(R.id.btn_consultar_Encuestas);
         Button boton_cerrar_sesion = findViewById(R.id.boton_cerrar_sesion);

        btn_consultar_editar_perfil_recp.setOnClickListener(v->
        {
            Intent i = new Intent(RecepcionistaActivity.this,
                    Consultar_Editar_Perfil.class);
            startActivity(i);
        });
        btn_consultar_ocupacion_hotel_recp.setOnClickListener(v->{
            Intent i= new Intent(RecepcionistaActivity.this, Consultar_Ocupacion_Hotel.class);
            startActivity(i);
        });

        btn_consultar_encuestas.setOnClickListener(v->{
            Intent i = new Intent(RecepcionistaActivity.this, Consultar_Encuestas_Satisfaccion.class);
            startActivity(i);
        });

        btn_add_reservas_hotel.setOnClickListener(v->{
            Intent i = new Intent(RecepcionistaActivity.this, Realizar_Reserva_Activity.class);
            startActivity(i);
        });
        boton_cerrar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RecepcionistaActivity.this, Pantalla_Inicio.class);
                Usuario.setInstance(null); // limpio lo que tenga el usuario

                Snackbar.make(v, "Sesión cerrada correctamente", Snackbar.LENGTH_SHORT).show();
                // limpio lo que exista en el historial de las activities, para q cdo comience este todo en cero!!
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);

            }
        });

        btn_gestionar_check_in_out.setOnClickListener(v->{
            Intent i= new Intent(RecepcionistaActivity.this,
                    GestionEntradasSalidasActivity.class);
            startActivity(i);
        });

        btn_consultar_listado_huspedes_recp.setOnClickListener(v->{
            Intent i = new Intent(RecepcionistaActivity.this, ConsultarHuespedesActivity.class);
            startActivity(i);
        });



    }
}