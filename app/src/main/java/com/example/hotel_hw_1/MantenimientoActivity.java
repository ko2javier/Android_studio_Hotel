package com.example.hotel_hw_1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MantenimientoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mantenimiento_menu);

        // defino botones
        Button btn_consultar_editar_perfil_mmto= findViewById(R.id.btn_consultar_editar_perfil_mmto);
        Button btn_consultar_tareas_pdtes_asigandas_mmto=
                findViewById(R.id.btn_consultar_tareas_pdtes_asigandas_mmto);
        Button btn_consultar_tareas_pdtes_mmto_hotel=
                findViewById(R.id.btn_consultar_tareas_pdtes_mmto_hotel);
        Button btn_consultar_Encuestas_mmto=
                findViewById(R.id.btn_consultar_Encuestas_mmto);
        // Pongo a la escucha

        btn_consultar_editar_perfil_mmto.setOnClickListener(v->
        {
            Intent i = new Intent(MantenimientoActivity.this,
                    Consultar_Editar_Perfil.class);
            startActivity(i);

        });

        btn_consultar_Encuestas_mmto.setOnClickListener(v->{
            Intent i = new Intent(MantenimientoActivity.this, Consultar_Encuestas_Satisfaccion.class);
            startActivity(i);
        });

    }
}