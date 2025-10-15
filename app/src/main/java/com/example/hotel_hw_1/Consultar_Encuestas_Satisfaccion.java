package com.example.hotel_hw_1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotel_hw_1.model.Usuario;

public class Consultar_Encuestas_Satisfaccion extends AppCompatActivity {

    private void mostrar_encuestas_por_rol(String tipoUser) {
        LinearLayout layout_recepcion = findViewById(R.id.linear_recepcion_encuestas);
        LinearLayout layout_mmto = findViewById(R.id.linear_mmto_encuestas);
        LinearLayout layout_limpieza = findViewById(R.id.linear_limpieza_encuestas);

        // Oculto o muestro en dependencia del usuario

        if ("gerente".equalsIgnoreCase(tipoUser)) {
            layout_recepcion.setVisibility(View.VISIBLE);
            layout_mmto.setVisibility(View.VISIBLE);
            layout_limpieza.setVisibility(View.VISIBLE);

        } else if ("mantenimiento".equalsIgnoreCase(tipoUser)) {
            layout_recepcion.setVisibility(View.GONE);
            layout_mmto.setVisibility(View.VISIBLE);
            layout_limpieza.setVisibility(View.GONE);

        } else if ("recepcionista".equalsIgnoreCase(tipoUser)){
            layout_recepcion.setVisibility(View.VISIBLE);
            layout_mmto.setVisibility(View.GONE);
            layout_limpieza.setVisibility(View.GONE);
        }else if ("limpieza".equalsIgnoreCase(tipoUser)){
            layout_recepcion.setVisibility(View.GONE);
            layout_mmto.setVisibility(View.GONE);
            layout_limpieza.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_consultar_encuestas_satisfaccion);

        // Defino botones
        Button btn_volver = findViewById(R.id.btn_volver);
        /* obtengo instacia del usuario
        * y en dependencia del rol * voy mostrando las encuentas  * que le tocaan o no*/
        Usuario usuario = Usuario.getInstance();
        String tipoUser = usuario.getTipo_usuario();


        mostrar_encuestas_por_rol(tipoUser);

        btn_volver.setOnClickListener(v->{
            finish();
        });





    }


}