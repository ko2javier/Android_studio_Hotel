/**
 * Autor: K. Jabier O'Reilly
 * Proyecto: Gestión de Hotel - Práctica 1ª Evaluación (PMDM 2025/2026)
 * Clase: GestionEntradasSalidasActivity.java
 * Descripción: Gestiona los procesos de Check-In y Check-Out de los huéspedes,
 *              validando los datos y actualizando el registro del hotel.
 * Centro: C.F.G.S. Desarrollo de Aplicaciones Multiplataforma
 * Módulo: Programación Multimedia y Dispositivos Móviles
 */

package com.example.hotel_hw_1;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotel_hw_1.model.Huesped;
import com.example.hotel_hw_1.model.HuespedData;
import com.google.android.material.snackbar.Snackbar;

public class GestionEntradasSalidasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gestion_entradas_salidas);

        //  Definimos varibles grales
        RadioGroup radioGroupOperacion = findViewById(R.id.radio_group_operacion);
        RadioButton rbCheckIn = findViewById(R.id.rb_check_in);
        RadioButton rbCheckOut = findViewById(R.id.rb_check_out);
        Button btnVolver = findViewById(R.id.btn_volver);

        // Ahora los linear
        LinearLayout layoutCheckIn = findViewById(R.id.linear_form_checkin);
        LinearLayout layoutCheckOut = findViewById(R.id.linear_buscar_checkout);

        // Los  Check-In
        EditText etNombre = findViewById(R.id.et_nombre_huesped);
        EditText etApellidos = findViewById(R.id.et_apellidos_huesped);
        EditText etTelefono = findViewById(R.id.et_telefono_huesped);
        EditText etHabitacion = findViewById(R.id.et_habitacion_huesped);
        Button btnCheckIn = findViewById(R.id.btn_realizar_check_in);

        // Los Check-Out
        EditText etNombreBuscar = findViewById(R.id.et_nombre_buscar);
        EditText etApellidosBuscar = findViewById(R.id.et_apellidos_buscar);
        Button btnBuscar = findViewById(R.id.btn_buscar_checkout);
        Button btnCheckOut = findViewById(R.id.btn_realizar_check_out);

        // hacemos los cambios entre  Check-In / Check-Out
        radioGroupOperacion.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rb_check_in) {
                layoutCheckIn.setVisibility(LinearLayout.VISIBLE);
                layoutCheckOut.setVisibility(LinearLayout.GONE);
            } else {
                layoutCheckIn.setVisibility(LinearLayout.GONE);
                layoutCheckOut.setVisibility(LinearLayout.VISIBLE);
            }
        });

        // REgistrar husped con el buton a la escucha!!
        btnCheckIn.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString().trim();
            String apellidos = etApellidos.getText().toString().trim();
            String telefono = etTelefono.getText().toString().trim();
            String habitacion = etHabitacion.getText().toString().trim();

            if (validaciones_campos(v, nombre, apellidos, telefono, habitacion)) return;


            Huesped nuevo = new Huesped(nombre, apellidos, telefono, habitacion);
            HuespedData.agregarHuesped(nuevo);
            Snackbar.make(v, "Exito Check-In realizado ", Snackbar.LENGTH_LONG).show();

            // Limpiar campos
            etNombre.setText("");
            etApellidos.setText("");
            etTelefono.setText("");
            etHabitacion.setText("");
        });

        //  Buscamos gest  para Check-Out
        btnBuscar.setOnClickListener(v -> {
            String nombre = etNombreBuscar.getText().toString().trim();
            String apellidos = etApellidosBuscar.getText().toString().trim();

            if (nombre.isEmpty() || apellidos.isEmpty()) {
                Snackbar.make(v, "Introduzca nombre y apellidos", Snackbar.LENGTH_SHORT).show();
                return;
            }

            Huesped h = HuespedData.buscarHuesped(nombre, apellidos);
            if (h != null && h.isCheckInActivo()) {
                btnCheckOut.setVisibility(Button.VISIBLE);
                Snackbar.make(v, "Huésped encontrado: " + h.getNombre() + " " + h.getApellidos(), Snackbar.LENGTH_SHORT).show();
            } else {
                btnCheckOut.setVisibility(Button.GONE);
                mostrarDialogoNoEncontrado();
            }
        });

        // Hacemos  Check-Out (cambiar estado)
        btnCheckOut.setOnClickListener(v -> {
            String nombre = etNombreBuscar.getText().toString().trim();
            String apellidos = etApellidosBuscar.getText().toString().trim();

            boolean exito = HuespedData.marcarCheckOut(nombre, apellidos);
            if (exito) {
                Snackbar.make(v, " Exito Check-Out realizado ", Snackbar.LENGTH_LONG).show();
                btnCheckOut.setVisibility(Button.GONE);
                etNombreBuscar.setText("");
                etApellidosBuscar.setText("");
            } else {
                Snackbar.make(v, " Check-Out Fallido", Snackbar.LENGTH_LONG).show();
            }
        });

        // 🔹 Volver
        btnVolver.setOnClickListener(v -> finish());
    }

    private static boolean validaciones_campos(View v, String nombre, String apellidos, String telefono, String habitacion) {
        if (nombre.length()<3 || apellidos.length()<3 ) {
            Snackbar.make(v, "Nombre/apellidos minimo 3 caracteres", Snackbar.LENGTH_SHORT).show();
            return true;
        }
        // Validamos phone, numeros no letras y 9 dig
        if (!telefono.matches("\\d{9}")) { // exactamente 9 dígitos
            Snackbar.make(v, "Teléfono inválido. Use 9 números.", Snackbar.LENGTH_SHORT).show();
            return true;
        }

        // Valido habitacion tiene que ser numero y entre 100 y 599!!
        if (!habitacion.matches("^[1-5]\\d{2}$")) {
            Snackbar.make(v, "Número de habitación inválido (100–599)", Snackbar.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    private void mostrarDialogoNoEncontrado() {
        new AlertDialog.Builder(this)
                .setTitle("Huésped no encontrado")
                .setMessage("No se encontró ningún huésped con esos datos.")
                .setPositiveButton("Aceptar", (dialog, which) -> dialog.dismiss())
                .show();
    }
}
