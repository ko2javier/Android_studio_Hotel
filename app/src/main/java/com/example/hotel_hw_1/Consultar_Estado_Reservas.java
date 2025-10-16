package com.example.hotel_hw_1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotel_hw_1.model.Usuario;

import java.util.List;

public class Consultar_Estado_Reservas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_consultar_estado_reservas);

        // defino variables


        Button btnVolver = findViewById(R.id.btn_volver_consultar_reservas);
        LinearLayout layoutReservasDinamicas= findViewById(R.id.layout_reservas_dinamicas);


        Usuario usuario = Usuario.getInstance();
        List<String> reservas = usuario.getReservas();

        // Añadimos nuevas reservas si el usuario lo hace!!!
        if (!reservas.isEmpty()) {
            for (String r : reservas) {
                TextView txt = new TextView(this);
                txt.setText(r);
                txt.setTextSize(16);
                txt.setPadding(10, 10, 10, 20);

                txt.setTextColor(getColor(android.R.color.holo_green_dark));
                layoutReservasDinamicas.addView(txt);
            }
        }
        btnVolver.setOnClickListener(v->{
            finish();
        });


    }
}