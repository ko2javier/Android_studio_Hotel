package com.example.hotel_hw_1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

    }
}