package com.example.hotel_hw_1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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


    }
}