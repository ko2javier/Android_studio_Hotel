/**
 * Autor: K. Jabier O'Reilly
 * Proyecto: Gestión de Hotel - Práctica 1ª Evaluación (PMDM 2025/2026)
 * Clase: ConsultarHuespedesActivity.java
 * Descripción: Muestra un listado de huéspedes del hotel usando un adaptador.
 * Centro: C.F.G.S. Desarrollo de Aplicaciones Multiplataforma
 * Módulo: Programación Multimedia y Dispositivos Móviles
 */

package com.example.hotel_hw_1.actividades;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotel_hw_1.R;
import com.example.hotel_hw_1.adaptadores.AdapterHuesped;
import com.example.hotel_hw_1.ropositorios.HuespedData;

public class ConsultarHuespedesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_huespedes);

        ListView listViewHuespedes = findViewById(R.id.list_view_huespedes);
        Button btnVolver = findViewById(R.id.btn_volver_listado);

        AdapterHuesped adapter = new AdapterHuesped(this, HuespedData.listarHuespedes());
        listViewHuespedes.setAdapter(adapter);

        btnVolver.setOnClickListener(v -> finish());
    }
}
