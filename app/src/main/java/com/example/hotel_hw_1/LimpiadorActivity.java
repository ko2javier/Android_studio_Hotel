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
        Button boton_cerrar_sesion= findViewById(R.id.boton_cerrar_sesion);


        //Pongo a la escucha
        btn_consulta_perfil.setOnClickListener(v -> {
            Intent i = new Intent(LimpiadorActivity.this, Consultar_Editar_Perfil.class);
            startActivity(i);
        });
        btn_consultar_encuestas_limpiador.setOnClickListener(v->{
            Intent i = new Intent(LimpiadorActivity.this, Consultar_Encuestas_Satisfaccion.class );
            startActivity(i);
        });

        boton_cerrar_sesion.setOnClickListener(v->{
            Intent i= new Intent(LimpiadorActivity.this, Pantalla_Inicio.class);
            Usuario.setInstance(null); // limpio lo que tenga el usuario

            Snackbar.make(v, "Sesión cerrada correctamente", Snackbar.LENGTH_SHORT).show();
            // limpio lo que exista en el historial de las activities, para q cdo comience este todo en cero!!
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);

        });

    }
}