package com.example.hotel_hw_1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotel_hw_1.model.AdapterHuesped;
import com.example.hotel_hw_1.model.HuespedData;

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
