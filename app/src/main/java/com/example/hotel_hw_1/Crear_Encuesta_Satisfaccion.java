package com.example.hotel_hw_1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class Crear_Encuesta_Satisfaccion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_crear_encuesta_satisfaccion);

    // Defimos variables !!
        RatingBar rt_ratingLimpieza= findViewById(R.id.ratingLimpieza);
        RatingBar rating_mantenimiento= findViewById(R.id.rating_mantenimiento);
        RatingBar rating_recepcion= findViewById(R.id.rating_recepcion);
        EditText edx_comentario_encuestas= findViewById(R.id.campo_comentario_encuestas);
        Button btn_enviar_encuesta= findViewById(R.id.btn_enviar_encuesta);
        Button btn_volver_encuesta= findViewById(R.id.btn_volver_encuesta);

        // Simulamos el envio de los datos de la encuesta a un sitio donde se recoja!!

        btn_enviar_encuesta.setOnClickListener(v->{

            float limp = rt_ratingLimpieza.getRating();
            float mant = rating_mantenimiento.getRating();
            float recep = rating_recepcion.getRating();
            String texto = edx_comentario_encuestas.getText().toString();

            // Simulación de envío , mostramos en pantalla el mismo!!
            String resumen = String.format(
                    "Encuesta enviada:\nLimpieza %.1f ★ | Mantenimiento %.1f ★ | Recepción %.1f ★",
                    limp, mant, recep
            );
            Snackbar.make(v, resumen, Snackbar.LENGTH_LONG).show();
            v.postDelayed(this::finish, 1800);
        });
        btn_volver_encuesta.setOnClickListener(v->{
            finish();
        });

    }
}