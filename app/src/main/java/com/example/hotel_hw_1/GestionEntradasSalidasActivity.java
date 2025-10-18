package com.example.hotel_hw_1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class GestionEntradasSalidasActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gestion_entradas_salidas);

        // Defino mi variables del layout
        RadioGroup radioGroupOperacion = findViewById(R.id.radio_group_operacion);
        RadioButton rbCheckIn = findViewById(R.id.rb_checkIn);
        RadioButton rbCheckOut = findViewById(R.id.rb_checkOut);

        TextView txtTituloOperacion = findViewById(R.id.txt_titulo_operacion);
        EditText etNombre = findViewById(R.id.et_nombre_gest);
        EditText etApellidos = findViewById(R.id.et_apellidos_gest);
        Button btnBuscar = findViewById(R.id.btn_buscar);

        LinearLayout linearDatosGest = findViewById(R.id.linear_datos_gest);
        EditText etNombreEncontrado = findViewById(R.id.et_nombre_huesped_encontrado);
        EditText etApellidosEncontrado = findViewById(R.id.et_apellidos_huesped_encontrado);
        EditText etTelefono = findViewById(R.id.et_telefono);
        EditText etNumeroRoom = findViewById(R.id.etx_numero_room);
        Button btnRealizarAccion = findViewById(R.id.btn_realizar_accion);
        Button btnVolver= findViewById(R.id.btnVolver);

        //  Cambio Texto en tiempo real según Check-In / Check-Out
        radioGroupOperacion.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rb_checkIn) {
                txtTituloOperacion.setText("Buscar Reserva:");
                btnRealizarAccion.setText("REALIZAR CHECK-IN");
                btnRealizarAccion.setBackgroundTintList(getColorStateList(android.R.color.holo_green_dark));
            } else {
                txtTituloOperacion.setText("Buscar Huesped:");
                btnRealizarAccion.setText("REALIZAR CHECK-OUT");
                btnRealizarAccion.setBackgroundTintList(getColorStateList(android.R.color.holo_red_dark));
            }
        });

        btnBuscar.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString().trim();
            String apellidos = etApellidos.getText().toString().trim();

            if (nombre.isEmpty() || apellidos.isEmpty()) {
                Snackbar.make(v, "Debe introducir nombre y apellidos", Snackbar.LENGTH_SHORT).show();
                ocultarPanelResultado(etNombreEncontrado, etApellidosEncontrado, etTelefono,
                        etNumeroRoom, linearDatosGest); // Ocultamos el panel si habia algo
                return;
            }

            // Mostramos la busqueda!!!
            if (nombre.equalsIgnoreCase("Diana") && apellidos.equalsIgnoreCase("Rio")) {
                // Mostramos los datos
                etNombreEncontrado.setText("Diana");
                etApellidosEncontrado.setText("Rio");
                etTelefono.setText("699999999");
                etNumeroRoom.setText("204");
                linearDatosGest.setVisibility(View.VISIBLE);
            } else {
                linearDatosGest.setVisibility(View.GONE);
                Snackbar.make(v, "No se encontro al huesped", Snackbar.LENGTH_SHORT).show();
            }
        });
// Resultados del check in / check out
        btnRealizarAccion.setOnClickListener(v -> {
            String accion = rbCheckIn.isChecked() ? "Check-In" : "Check-Out";
            Snackbar.make(v, accion + " realizado correctamente ", Snackbar.LENGTH_LONG).show();
            linearDatosGest.setVisibility(View.GONE);

            // Limpiamos campos de búsqueda
            etNombre.setText("");
            etApellidos.setText("");
        });
        btnVolver.setOnClickListener(v -> {
            finish();
        });
    }

    private static void ocultarPanelResultado
            (EditText etNombreEncontrado, EditText etApellidosEncontrado, EditText etTelefono,
             EditText etNumeroRoom, LinearLayout linearDatosGest) {
        etNombreEncontrado.setText("");
        etApellidosEncontrado.setText("");
        etTelefono.setText("");
        etNumeroRoom.setText("");
        linearDatosGest.setVisibility(View.GONE);
    }



}