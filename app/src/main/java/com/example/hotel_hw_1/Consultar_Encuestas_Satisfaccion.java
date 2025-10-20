package com.example.hotel_hw_1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotel_hw_1.model.AdaptadorEncuesta;
import com.example.hotel_hw_1.model.Encuesta;
import com.example.hotel_hw_1.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Consultar_Encuestas_Satisfaccion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_consultar_encuestas_satisfaccion);

        ListView listaEncuestas = findViewById(R.id.lista_encuestas);
        Button btnVolver = findViewById(R.id.btn_volver);

        Usuario usuario = Usuario.getInstance();
        String tipoUser = usuario.getTipo_usuario();

        List<Encuesta> encuestas = new ArrayList<>();
        encuestas.add(new Encuesta("Atención en Recepción", 4.5f, 3, true));
        encuestas.add(new Encuesta("Instalaciones y Equipos", 4.0f, 8, true));
        encuestas.add(new Encuesta("Limpieza y Orden", 4.0f, 3, true));

        //  Ocultamos según rol
        for (Encuesta e : encuestas) {
            switch (tipoUser.toLowerCase()) {
                case "recepcionista":
                    if (!e.getCategoria().contains("Recepción")) e.setVisible(false);
                    break;
                case "mantenimiento":
                    if (!e.getCategoria().contains("Instalaciones")) e.setVisible(false);
                    break;
                case "limpieza":
                    if (!e.getCategoria().contains("Limpieza")) e.setVisible(false);
                    break;
                case "gerente":
                    e.setVisible(true);
                    break;
            }
        }

        //  Filtramos solo las visibles
        List<Encuesta> encuestasFiltradas = new ArrayList<>();
        for (Encuesta e : encuestas) {
            if (e.isVisible()) encuestasFiltradas.add(e);
        }

        AdaptadorEncuesta adaptador = new AdaptadorEncuesta(this, encuestasFiltradas);
        listaEncuestas.setAdapter(adaptador);

        btnVolver.setOnClickListener(v -> finish());
    }
}
