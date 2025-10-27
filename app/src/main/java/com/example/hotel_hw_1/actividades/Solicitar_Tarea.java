/**
 * Autor: K. Jabier O'Reilly
 *
 *
 */

package com.example.hotel_hw_1.actividades;



import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotel_hw_1.R;

import com.example.hotel_hw_1.modelos.Validaciones;
import com.google.android.material.snackbar.Snackbar;

public class Solicitar_Tarea extends AppCompatActivity {

    private EditText etx_numero_room;
           private TextView txt_error_habitacion;
    private  RadioGroup radioGroupTarea;
    private  Spinner spinnerZona, spinnerPasillo;
    private Button btnEnviar, btnVolver;

    private static String[] pasillos = null, zonasLimpieza = null, zonasMantenimiento = null;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_solicitar_tarea);

        // Asigno id a mis variables

        radioGroupTarea = findViewById(R.id.radioGroupTarea);
        etx_numero_room = findViewById(R.id.etx_numero_room);
        txt_error_habitacion= findViewById(R.id.txt_error_habitacion);
        spinnerZona = findViewById(R.id.spinnerZona);
        spinnerPasillo = findViewById(R.id.spinnerPasillo);
        btnEnviar = findViewById(R.id.btn_enviar_solicitud);
        btnVolver = findViewById(R.id.btn_volver);

        // Defino arrays
        pasillos = new String[]{"Norte", "Sur", "Este", "Oeste"};
        zonasLimpieza = new String[]{"Salón", "Dormitorio", "Baño", "General"};
        zonasMantenimiento = new String[]{"Salón", "Dormitorio", "Baño"};

        // Adapter para  spinner pasillos
        ArrayAdapter<String> adapterPasillo = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, pasillos);

        adapterPasillo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPasillo.setAdapter(adapterPasillo);

        // Adapter para  spinner Zona. Por defecto Limpieza es la opcion!
        ArrayAdapter<String> adapterZona = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, zonasLimpieza);
        adapterZona.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerZona.setAdapter(adapterZona);

        /* Como tengo 2 zonas Limpieza y Matenimiento en base a esto
        * debo de cambiar las opciones que debe tomas el cliente
        * modifico a mantenimiento/limpieza en base a que tome el radioButton
        * */
        radioGroupTarea.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rb_limpieza) {
                ArrayAdapter<String> adapterLimpieza = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, zonasLimpieza);
                adapterLimpieza.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerZona.setAdapter(adapterLimpieza);

            } else if (checkedId == R.id.rb_mmto) {
                ArrayAdapter<String> adapterMmto = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, zonasMantenimiento);
                adapterMmto.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerZona.setAdapter(adapterMmto);
            }
        });

        // Listener botón Enviar
        btnEnviar.setOnClickListener(v -> {
            /*
            * reviso mis campo habitación con Validar !!! */
            boolean ok = Validaciones.validarHabitacionObligatoria(v, etx_numero_room, txt_error_habitacion);
            if (!ok) return;

            Snackbar.make(v, "Solicitud enviada correctamente", Snackbar.LENGTH_SHORT).show();
            etx_numero_room.setText("");

        });

        // Listener botón Volver
        btnVolver.setOnClickListener(v -> finish());
    }

    private void validaciones_de_limpieza(View v) {
        // Por ahora no validamos nada
        Snackbar.make(v, "Solicitud enviada correctamente", Snackbar.LENGTH_SHORT).show();

        // Limpiar campos
        etx_numero_room.setText("");
        spinnerZona.setSelection(0);
        spinnerPasillo.setSelection(0);
        radioGroupTarea.check(R.id.rb_limpieza);
    }

}
