package com.example.hotel_hw_1;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotel_hw_1.model.ReservaData;
import com.example.hotel_hw_1.model.Usuario;
import com.google.android.material.snackbar.Snackbar;

public class Realizar_Reserva_Activity extends AppCompatActivity {

    // Metodo para verificar la reserva realizada
    private void confirmar_reserva(View v, RadioGroup radio_group, CheckBox checkbox_spa, CheckBox checkbox_parking, CheckBox checkbox_mmto, EditText edit_fecha, TextView txt_disponibilidad_actual) {
        int idSeleccionado = radio_group.getCheckedRadioButtonId();
        if (idSeleccionado == -1) {
            Snackbar.make(v, "Seleccione un tipo de habitación", Snackbar.LENGTH_SHORT).show();
            return;
        }

        // Determino el tipo de habit seleccionada
        RadioButton rbSeleccionado = findViewById(idSeleccionado);
        String tipoHabitacion = rbSeleccionado.getText().toString();



        // Servicios adicionales
        StringBuilder servicios = new StringBuilder();
        if (checkbox_spa.isChecked()) servicios.append("Spa ");
        if (checkbox_parking.isChecked()) servicios.append("Parking ");
        if (checkbox_mmto.isChecked()) servicios.append("Mantenimiento ");
        if (servicios.length() == 0) servicios.append("Sin servicios adicionales");

        // Determinamos si la fecha cumple con el patron adecuado!!

        String fecha = edit_fecha.getText().toString().trim();
        String patronFecha = "\\d{2}-\\d{2}-\\d{4}"; // Formato DD-MM-YYYY

        if (!fecha.matches(patronFecha)) {
            Snackbar.make(v, "Formato de fecha inválido. Use DD-MM-YYYY", Snackbar.LENGTH_SHORT).show();
            edit_fecha.requestFocus(); // Retornamos el focus a fecha
            return;
        }
        // Despues de confirmado todo, guardo la reserva en el usuario!!
        String detalles = "Fecha: " + fecha +
                " | Habitación: " + tipoHabitacion +
                " | Servicios: " + servicios +
                " | Estado: Confirmada";
        Usuario.getInstance().agregarReserva(detalles); // agrego la reserva!!


        // Consultamos si se puede reservar y modificamos valores almacenados!!
        boolean reservado = ReservaData.reservar(tipoHabitacion);
        if (reservado) {
            Snackbar.make(v, "Reserva confirmada para " + fecha +
                    "\nHabitación " + tipoHabitacion +
                    "\nServicios: " + servicios, Snackbar.LENGTH_LONG).show();
            txt_disponibilidad_actual.setText("Disponibilidad actual:\n" + ReservaData.mostrarDisponibilidad());
        }
        // damos unos segundos y volvemos al huesped!!
        v.postDelayed(this::finish, 1800);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_realizar_reserva);


     // Defino variables del xml
        EditText edit_fecha = findViewById(R.id.edit_fecha);
        RadioGroup radio_group= findViewById(R.id.radioGroupHabitacion);
        RadioButton rbSimple= findViewById(R.id.rbSimple);
        RadioButton rbDoble = findViewById(R.id.rbDoble);
        RadioButton rbTriple= findViewById(R.id.rbTriple);
        CheckBox checkbox_spa= findViewById(R.id.checkbox_spa);
        CheckBox checkbox_parking= findViewById(R.id.checkbox_parking);
        CheckBox checkbox_mmto= findViewById(R.id.checkbox_mmto);
        Button btn_confirmar_reserva= findViewById(R.id.btn_confirmar_reserva_flat);
        Button btn_volver_reserva_flat = findViewById(R.id.btn_volver_reserva_flat);
        TextView txt_disponibilidad_actual = findViewById(R.id.txt_disponibilidad_actual);
        LinearLayout linearPlanta = findViewById(R.id.linear_planta_reserva);
        AutoCompleteTextView autoPlanta = findViewById(R.id.auto_planta);

        // Mostrar disponibilidad inicial
        txt_disponibilidad_actual.setText("Disponibilidad actual:\n" + ReservaData.mostrarDisponibilidad());

        // Obtener usuario actual
        Usuario usuario = Usuario.getInstance();
      // Cmprobamos el tipo de usuario y oculatamos para huesped!!
        if (usuario.getTipo_usuario().equalsIgnoreCase("recepcionista")) {
            linearPlanta.setVisibility(View.VISIBLE); // HAcemos visible solo para recepcionista

            String[] plantas = {"Planta 1", "Planta 2", "Planta 3", "Planta 4", "Planta 5"};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_dropdown_item_1line, plantas);
            autoPlanta.setAdapter(adapter);
        } else {
            linearPlanta.setVisibility(View.GONE); // 🔹 Ocultar completamente para huéspedes
        }
        // Establecemos los listeners

        btn_volver_reserva_flat.setOnClickListener(v->{
            finish();
        });

        btn_confirmar_reserva.setOnClickListener(v -> {

            confirmar_reserva(v, radio_group, checkbox_spa, checkbox_parking, checkbox_mmto, edit_fecha, txt_disponibilidad_actual);
        });



    }


}