package com.example.hotel_hw_1;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotel_hw_1.model.Tarea;
import com.example.hotel_hw_1.model.TareaData;
import com.google.android.material.snackbar.Snackbar;

public class Solicitar_Tarea extends AppCompatActivity {

    /* Metodo que me da tanto las
    * validaciones de que tarea selecciono como
    * los snackbars cdo se hizo efectiva
    * */
    private static void validaciones_de_limpieza(View v, EditText etx_numero_room, AutoCompleteTextView autoZona, AutoCompleteTextView autoPasillo, RadioGroup radioGroupTarea, String[] zonasLimpieza, String[] zonasMantenimiento, String[] pasillos) {
        String habitacionStr = etx_numero_room.getText().toString().trim();
        String zona = autoZona.getText().toString().trim();
        String pasillo = autoPasillo.getText().toString().trim();

        int checkedId = radioGroupTarea.getCheckedRadioButtonId();
        String tipoTarea = "";
        if (checkedId == R.id.rb_limpieza) tipoTarea = "Limpieza";
        else if (checkedId == R.id.rb_mmto) tipoTarea = "Mantenimiento";


        // Validar habitación (rango 100–599)
        int habitacion;
        try {
            habitacion = Integer.parseInt(habitacionStr);
            if (habitacion < 100 || habitacion > 599) {
                Snackbar.make(v, "Número de habitación fuera de rango (100–599)", Snackbar.LENGTH_SHORT).show();
                return;
            }
        } catch (NumberFormatException e) {
            Snackbar.make(v, "Número de habitación inválido", Snackbar.LENGTH_SHORT).show();
            return;
        }

        // Validar zona según tipo de tarea
        boolean zonaValida = false;
        if (tipoTarea.equals("Limpieza")) {
            for (String z : zonasLimpieza) if (z.equalsIgnoreCase(zona)) zonaValida = true;
        } else {
            for (String z : zonasMantenimiento) if (z.equalsIgnoreCase(zona)) zonaValida = true;
        }

        if (!zonaValida) {
            Snackbar.make(v, "Zona inválida.Escriba Salón \\Dormitorio \\Baño.", Snackbar.LENGTH_SHORT).show();
            return;
        }

        // Validar pasillo (si no está vacío)
        if (!pasillo.isEmpty()) {
            boolean pasilloValido = false;
            for (String p : pasillos) if (p.equalsIgnoreCase(pasillo)) pasilloValido = true;
            if (!pasilloValido) {
                Snackbar.make(v, "Pasillo inválido. Use Norte, Sur, Este u Oeste.", Snackbar.LENGTH_SHORT).show();
                return;
            }
        }

        // Si todo es válido, creo la tarea
        Tarea nuevaTarea = new Tarea(tipoTarea, "-", String.valueOf(habitacion), pasillo);
        TareaData.agregarTarea(nuevaTarea);

        Snackbar.make(v, "Solicitud enviada correctamente ", Snackbar.LENGTH_SHORT).show();

        // Limpiamos los campos
        etx_numero_room.setText("");
        autoZona.setText("");
        autoPasillo.setText("");
        radioGroupTarea.check(R.id.rb_limpieza);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_solicitar_tarea);

        //defino variables
        RadioGroup radioGroupTarea = findViewById(R.id.radioGroupTarea);

        EditText etx_numero_room = findViewById(R.id.etx_numero_room);
        AutoCompleteTextView autoZona = findViewById(R.id.autoZona);
        AutoCompleteTextView autoPasillo = findViewById(R.id.autoPasillo);
        Button btnEnviar = findViewById(R.id.btn_enviar_solicitud);
        Button btnVolver = findViewById(R.id.btn_volver);


        // Defino el autocompletar de  Pasillos
        String[] pasillos = {"Norte", "Sur", "Este", "Oeste"};
        autoPasillo.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, pasillos));

        // Defino los autodapter de Zonas
        String[] zonasLimpieza = {"Salón", "Dormitorio", "Baño", "General"};
        String[] zonasMantenimiento = {"Salón", "Dormitorio", "Baño"};

        // Cambiar opciones según tarea seleccionada
        radioGroupTarea.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rb_limpieza) {
                autoZona.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, zonasLimpieza));
            } else {
                autoZona.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, zonasMantenimiento));
            }
        });

        // Por defecto al comienzo seteo a Limpieza
        autoZona.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, zonasLimpieza));

        // Pongo a la escucha el boton de solicitar limpieza/mmto

        btnEnviar.setOnClickListener(v -> {

            validaciones_de_limpieza(v, etx_numero_room, autoZona, autoPasillo, radioGroupTarea, zonasLimpieza, zonasMantenimiento, pasillos);
        });

        btnVolver.setOnClickListener(v -> finish());
    }

}