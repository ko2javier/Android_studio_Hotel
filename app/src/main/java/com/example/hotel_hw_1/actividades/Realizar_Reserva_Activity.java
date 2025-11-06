/**
 * Autor: K. Jabier O'Reilly
 *
 */

package com.example.hotel_hw_1.actividades;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotel_hw_1.R;
import com.example.hotel_hw_1.modelos.ValidadorReserva;
import com.example.hotel_hw_1.repositorios.ReservaData;
import com.example.hotel_hw_1.modelos.Usuario;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

public class Realizar_Reserva_Activity extends AppCompatActivity {
    private RadioButton rbSeleccionado, rbSimple, rbDoble, rbTriple,
            rb_media_pension, rb_pension_full;
    private CheckBox checkbox_spa, checkbox_parking;
    private RadioGroup radio_group, radiog_type_pension;
    private   EditText edit_fecha, etNombreHuesped, et_apellidos;
    private TextView txt_disponibilidad_actual;
    private Button btn_confirmar_reserva, btn_volver_reserva_flat;


    // Metodo para verificar la reserva realizada
    private void confirmar_reserva(View v) {
        // Paso 1 : validar formulario completo antes de continuar
        boolean esValido = ValidadorReserva.validarFormulario( this, v, radio_group,
                rbSimple, rbDoble, rbTriple, edit_fecha, etNombreHuesped, et_apellidos
        );

        if (!esValido) return; // Detenemos si hay errores

        // Paso 2 : obtener datos ya seguros
        int idSeleccionado = radio_group.getCheckedRadioButtonId();
        rbSeleccionado = findViewById(idSeleccionado);
        String tipoHabitacion = rbSeleccionado.getText().toString();

        int idPensionSeleccionada = radiog_type_pension.getCheckedRadioButtonId();

        // paso 3  Servicios adicionales
        StringBuilder servicios = new StringBuilder();
        if (checkbox_spa.isChecked()) servicios.append("Spa ");
        if (checkbox_parking.isChecked()) servicios.append("Parking ");
        if (idPensionSeleccionada != -1) {
            RadioButton rbPensionSeleccionada = findViewById(idPensionSeleccionada);
            servicios.append(rbPensionSeleccionada.getText().toString()).append(" ");
        }
        if (servicios.length() == 0) servicios.append("Sin servicios adicionales");

        // Paso 4  Fecha y huésped
        String fecha = edit_fecha.getText().toString().trim();
        String nombreHuesped = "";
        String apellidos = "";

        if (Usuario.getInstance().getTipo_usuario().equalsIgnoreCase("recepcionista")) {
            nombreHuesped = etNombreHuesped.getText().toString().trim();
            apellidos = et_apellidos.getText().toString().trim();
        }

        //  Paso 5 Crear detalles de la reserva
        String detalles = "";
        if (Usuario.getInstance().getTipo_usuario().equalsIgnoreCase("recepcionista")) {
            detalles = "Cliente: " + nombreHuesped + " (" + apellidos + ") | ";
        }

        detalles += "Fecha: " + fecha +
                " | Habitación: " + tipoHabitacion +
                " | Servicios: " + servicios +
                " | Estado: Confirmada";

        // Guardar la reserva
        Usuario.getInstance().agregarReserva(detalles);

        //  PAso 6 Actualizar disponibilidad y notificar
        boolean reservado = ReservaData.reservar(tipoHabitacion);
        if (reservado) {
            Snackbar.make(v, "Reserva confirmada para " + fecha +
                    "\nHabitación " + tipoHabitacion +
                    "\nServicios: " + servicios, Snackbar.LENGTH_LONG).show();

            txt_disponibilidad_actual.setText("Disponibilidad actual:\n" + ReservaData.mostrarDisponibilidad());
        }

        // PAso 7 Cerrar tras unos segundos
        v.postDelayed(this::finish, 2000);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_realizar_reserva);


     // Defino variables del xml
         etNombreHuesped = findViewById(R.id.et_nombre_huesped);
        et_apellidos = findViewById(R.id.et_apellidos_huesped);
         edit_fecha = findViewById(R.id.edit_fecha);
        radio_group= findViewById(R.id.radioGroupHabitacion);
         rbSimple= findViewById(R.id.rbSimple);
         rbDoble = findViewById(R.id.rbDoble);
         rbTriple= findViewById(R.id.rbTriple);
         checkbox_spa= findViewById(R.id.checkbox_spa);
         checkbox_parking= findViewById(R.id.checkbox_parking);
         radiog_type_pension= findViewById(R.id.radiog_type_pension);
         rb_media_pension= findViewById(R.id.rb_media_pension);
        rb_pension_full= findViewById(R.id.rb_pension_full);
        btn_confirmar_reserva= findViewById(R.id.btn_confirmar_reserva_flat);
        btn_volver_reserva_flat = findViewById(R.id.btn_volver_reserva_flat);
         txt_disponibilidad_actual = findViewById(R.id.txt_disponibilidad_actual);
        LinearLayout linearPlanta = findViewById(R.id.linear_planta_reserva);
        Spinner spinnerPlanta = findViewById(R.id.spinner_planta);


        // Mostrar disponibilidad inicial
        txt_disponibilidad_actual.setText("Disponibilidad actual:\n" + ReservaData.mostrarDisponibilidad());

        // Obtener usuario actual
        Usuario usuario = Usuario.getInstance();
      // Comprobamos el tipo de usuario y ocultamos para huesped!!
        if (usuario.getTipo_usuario().equalsIgnoreCase("recepcionista")) {
            linearPlanta.setVisibility(View.VISIBLE); // HAcemos visible solo para recepcionista
            etNombreHuesped.setVisibility(View.VISIBLE);
            et_apellidos.setVisibility(View.VISIBLE);

            // declaro mi spinner
            String[] plantas = {"Planta 1", "Planta 2", "Planta 3", "Planta 4", "Planta 5"};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_dropdown_item_1line, plantas);
            spinnerPlanta.setAdapter(adapter);
        } else {
            linearPlanta.setVisibility(View.GONE); // Ocultar completamente para huéspedes
        }
        // Establecemos los listeners

        btn_volver_reserva_flat.setOnClickListener(v->{
            finish();
        });

        btn_confirmar_reserva.setOnClickListener(v -> {
            confirmar_reserva(v);
        });

        // ---  Pongo a la escucha el Selector de fecha DatePickerDialog ---
        edit_fecha.setOnClickListener(v -> {
            reservar_fecha();
        });

    }

    /* Reservo la fecha adecuada, con los limitadores correspondientes*/
    private void reservar_fecha() {
        final Calendar calen = Calendar.getInstance();
        int year = calen.get(Calendar.YEAR);
        int month = calen.get(Calendar.MONTH);
        int day = calen.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePicker = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    // Le doy formato a mi Fecha
                    String fechaSeleccionada = String.format("%02d-%02d-%04d",
                            selectedDay, (selectedMonth + 1), selectedYear);
                    edit_fecha.setText(fechaSeleccionada);
                },
                year, month, day
        );

        // Limitar fechas válidas
        Calendar minDate = Calendar.getInstance(); // hoy
        minDate.add(Calendar.DAY_OF_MONTH, 2);
        datePicker.getDatePicker().setMinDate(minDate.getTimeInMillis());

        Calendar maxDate = Calendar.getInstance();
        maxDate.add(Calendar.YEAR, 2); // 2 años por encima!!!
        datePicker.getDatePicker().setMaxDate(maxDate.getTimeInMillis());

        datePicker.show();
    }


}