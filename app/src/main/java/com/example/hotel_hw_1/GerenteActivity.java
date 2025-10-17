package com.example.hotel_hw_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.example.hotel_hw_1.model.Usuario;
import com.google.android.material.snackbar.Snackbar;

public class GerenteActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerente_menu);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.gerente_root), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // defino mis botones para despues ponerlos a la escucha
        Button btn_consultar_perfil= findViewById(R.id.btn_consultar_editar_perfil);
        Button btn_consultar_ocupacion= findViewById(R.id.btn_consultar_ocupacion_hotel);
        Button btn_consultar_encuestas_gerente= findViewById(R.id.btn_consultar_encuestas_gerente);
        Button boton_cerrar_sesion = findViewById(R.id.boton_cerrar_sesion);


        // comienzo con los listeners !!!!

        btn_consultar_perfil.setOnClickListener(new View.OnClickListener() {
            Usuario usuario = Usuario.getInstance();

            @Override
            public void onClick(View v) {
                Intent i = new Intent(GerenteActivity.this, Consultar_Editar_Perfil.class);

                startActivity(i);
            }
        });
        btn_consultar_ocupacion.setOnClickListener(v->{
            Intent i = new Intent(GerenteActivity.this, Consultar_Ocupacion_Hotel.class);
            startActivity(i);
        });
        btn_consultar_encuestas_gerente.setOnClickListener(v->{
            Intent i = new Intent(GerenteActivity.this, Consultar_Encuestas_Satisfaccion.class);
            startActivity(i);
        });
        boton_cerrar_sesion.setOnClickListener(v->{
            Intent i = new Intent(GerenteActivity.this, Pantalla_Inicio.class);
            Usuario.setInstance(null); // limpio lo que tenga el usuario

            Snackbar.make(v, "Sesión cerrada correctamente", Snackbar.LENGTH_SHORT).show();
            // limpio lo que exista en el historial de las activities, para q cdo comience este todo en cero!!
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);Usuario.setInstance(null); // limpio lo que tenga el usuario

        });

    }
}