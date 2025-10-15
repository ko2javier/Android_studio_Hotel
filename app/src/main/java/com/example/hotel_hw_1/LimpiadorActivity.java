package com.example.hotel_hw_1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LimpiadorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limpiador_menu);

        //defino botones
        Button btn_consulta_perfil= findViewById(R.id.btn_consultar_editar_perfil_limp);
        Button btn_consultar_tareas_asignadas_pdtes=
                findViewById(R.id.btn_consultar_tareas_pdtes_asigandas_limpiador);
        Button btn_consultar_tares_pdtes_sin_asignar=
                findViewById(R.id.btn_consultar_tareas_pdtes_limpieza_hotel);
        Button btn_consultar_encuestas_limpiador= findViewById(R.id.btn_consultar_Encuestas_limpiador);

        //Pongo a la escucha
        btn_consulta_perfil.setOnClickListener(v -> {
            Intent i = new Intent(LimpiadorActivity.this, Consultar_Editar_Perfil.class);
            startActivity(i);
        });
        btn_consultar_encuestas_limpiador.setOnClickListener(v->{
            Intent i = new Intent(LimpiadorActivity.this, Consultar_Encuestas_Satisfaccion.class );
            startActivity(i);
        });


    }
}