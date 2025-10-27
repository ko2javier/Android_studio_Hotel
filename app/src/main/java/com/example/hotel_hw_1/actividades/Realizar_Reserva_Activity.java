/**
 * Autor: K. Jabier O'Reilly
 * Proyecto: Gestión de Hotel - Práctica 1ª Evaluación (PMDM 2025/2026)
 * Clase: Realizar_Reserva_Activity.java
 * Descripción: Permite realizar reservas tanto para huéspedes como recepcionistas.
 *              Incluye selección de habitación, servicios, fecha y validaciones de campos.
 * Centro: C.F.G.S. Desarrollo de Aplicaciones Multiplataforma
 * Módulo: Programación Multimedia y Dispositivos Móviles
 */

package com.example.hotel_hw_1.actividades;

import android.app.DatePickerDialog;
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

import com.example.hotel_hw_1.R;
import com.example.hotel_hw_1.repositorios.ReservaData;
import com.example.hotel_hw_1.model.Usuario;
import com.google.android.material.snackbar.Snackbar;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class Realizar_Reserva_Activity extends AppCompatActivity {

    // Metodo para verificar la reserva realizada
    private void confirmar_reserva(View v, RadioGroup radio_group, CheckBox checkbox_spa, CheckBox checkbox_parking,
                                   RadioGroup  radiog_type_pension, EditText edit_fecha, TextView txt_disponibilidad_actual,
                                   AutoCompleteTextView autoPlanta, EditText etNombreHuesped,  EditText et_apellidos ) {
        int idSeleccionado = radio_group.getCheckedRadioButtonId();
        if (idSeleccionado == -1) {
            Snackbar.make(v, "Seleccione un tipo de habitación", Snackbar.LENGTH_SHORT).show();
            return;
        }

        // Determino el tipo de habitacion seleccionada
        RadioButton rbSeleccionado = findViewById(idSeleccionado);
        String tipoHabitacion = rbSeleccionado.getText().toString();
        // Determino tipo de pension. y lo completo en la linea 49!!
        int idPensionSeleccionada = radiog_type_pension.getCheckedRadioButtonId();

        // Servicios adicionales
        StringBuilder servicios = new StringBuilder();
        if (checkbox_spa.isChecked()) servicios.append("Spa ");
        if (checkbox_parking.isChecked()) servicios.append("Parking ");
        if (idPensionSeleccionada != -1) {
            RadioButton rbPensionSeleccionada = findViewById(idPensionSeleccionada);
            servicios.append(rbPensionSeleccionada.getText().toString()).append(" ");
        }

        if (servicios.length() == 0) servicios.append("Sin servicios adicionales");

        // Determinamos si la fecha cumple con el patron adecuado!!

        String fecha = edit_fecha.getText().toString().trim();
        // Si la fecha esta vacia retornamos
        if (fecha.isEmpty()) {
            Snackbar.make(v, "Rellene el campo fecha", Snackbar.LENGTH_SHORT).show();
            edit_fecha.requestFocus(); // Retornamos el focus a fecha
            return;
        }
        /* Validos campos para recepcionista
        * comienzo por la planta
        * */
        // determnino que el user solicito la planta adecuada para recepcionista !!! huesped no aplica!!
        String planta = autoPlanta.getText().toString().trim();
        List<String> plantasValidas = Arrays.asList("Planta 1", "Planta 2", "Planta 3", "Planta 4", "Planta 5");

        if (Usuario.getInstance().getTipo_usuario().equalsIgnoreCase("recepcionista")
                && !plantasValidas.contains(planta)) {
            Snackbar.make(v, "Seleccione una planta válida", Snackbar.LENGTH_SHORT).show();
            autoPlanta.requestFocus();
            return;
        }

        String nombreHuesped = "";
        String apellidos = "";

        // Si el usuario es recepcionista, pedimos los datos del huésped
        if (Usuario.getInstance().getTipo_usuario().equalsIgnoreCase("recepcionista")) {
            nombreHuesped = etNombreHuesped.getText().toString().trim();
            apellidos = et_apellidos.getText().toString().trim();

            if (nombreHuesped.length()<3 || apellidos.length()<3) {
                Snackbar.make(v, "Complete los datos del huésped", Snackbar.LENGTH_SHORT).show();
                return;
            }
        }
        // Despues de confirmado todo, guardo la reserva en el usuario!!
        String detalles = "";
        // si es recepcionista guardo los detalles del cliente!
        if (Usuario.getInstance().getTipo_usuario().equalsIgnoreCase("recepcionista")) {
            detalles = "Cliente: " + nombreHuesped + " (" + apellidos+ ") | ";
        }

        detalles += "Fecha: " + fecha +
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
        v.postDelayed(this::finish, 2000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_realizar_reserva);


     // Defino variables del xml
        EditText etNombreHuesped = findViewById(R.id.et_nombre_huesped);
        EditText et_apellidos = findViewById(R.id.et_apellidos_huesped);
        EditText edit_fecha = findViewById(R.id.edit_fecha);
        RadioGroup radio_group= findViewById(R.id.radioGroupHabitacion);
        RadioButton rbSimple= findViewById(R.id.rbSimple);
        RadioButton rbDoble = findViewById(R.id.rbDoble);
        RadioButton rbTriple= findViewById(R.id.rbTriple);
        CheckBox checkbox_spa= findViewById(R.id.checkbox_spa);
        CheckBox checkbox_parking= findViewById(R.id.checkbox_parking);
        RadioGroup radiog_type_pension= findViewById(R.id.radiog_type_pension);
        RadioButton rb_media_pension= findViewById(R.id.rb_media_pension);
        RadioButton rb_pension_full= findViewById(R.id.rb_pension_full);
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
            etNombreHuesped.setVisibility(View.VISIBLE);
            et_apellidos.setVisibility(View.VISIBLE);

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

            confirmar_reserva(v, radio_group, checkbox_spa, checkbox_parking, radiog_type_pension,
                    edit_fecha, txt_disponibilidad_actual,  autoPlanta, etNombreHuesped, et_apellidos);
        });

        // ---  Pongo a la escucha el Selector de fecha DatePickerDialog ---
        edit_fecha.setOnClickListener(v -> {
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

            datePicker.show();
        });




    }


}