/**
 * Autor: K. Jabier O'Reilly
 * Proyecto: Gestión de Hotel - Práctica 1ª Evaluación (PMDM 2025/2026)
 * Clase: EmpleadoFormActivity.java
 * Descripción: Pantalla para añadir o editar empleados del hotel.
 *              Incluye validaciones de campos y manejo del modo edición.
 * Centro: C.F.G.S. Desarrollo de Aplicaciones Multiplataforma
 * Módulo: Programación Multimedia y Dispositivos Móviles
 */
package com.example.hotel_hw_1;

import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotel_hw_1.model.Empleado;
import com.example.hotel_hw_1.model.EmpleadoData;
import com.google.android.material.snackbar.Snackbar;

public class EmpleadoFormActivity extends AppCompatActivity {
    // defino variables
    private EditText etNombre, etApellidos, etRol, etEmail, etTelefono;
    private Button btnGuardar, btnCancelar;

    private boolean modoEditar = false;
    private int posicionEditar = -1;
    private Empleado empleadoActual = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_empleado_form);

        // Les asigno id a las variables
        etNombre = findViewById(R.id.et_nombre_empleado);
        etApellidos = findViewById(R.id.et_apellidos_empleado);
        etRol = findViewById(R.id.et_rol_empleado);
        etEmail = findViewById(R.id.et_email_empleado);
        etTelefono = findViewById(R.id.et_telefono_empleado);
        btnGuardar = findViewById(R.id.btn_guardar);
        btnCancelar = findViewById(R.id.btn_cancelar);

        /*Verifico que el bundle no sea nulo y me
        traiga la posicion del array para mostrar datos del empleado!!!
        * */
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey("posicion")) {
            modoEditar = true;
            posicionEditar = extras.getInt("posicion");
            empleadoActual = EmpleadoData.getEmpleados().get(posicionEditar);
            rellenarCampos();
        }

        btnGuardar.setOnClickListener(v -> guardarEmpleado(v));
        btnCancelar.setOnClickListener(v -> finish());
    }
    // Metodo para mostrar los datos del empleado!!
    private void rellenarCampos() {
        etNombre.setText(empleadoActual.getNombre());
        etApellidos.setText(empleadoActual.getApellidos());
        etRol.setText(empleadoActual.getRol());
        etEmail.setText(empleadoActual.getEmail());
        etTelefono.setText(empleadoActual.getTelefono());
    }
/*
* En este metodo defino todo lo necesario
* para guardar los datos del empleado con validaciones incluidaas.
* Esto tambien incluye añadir nuevos usuarios con
* sus validaciones de campos incluidas.
* divido el metodo en añadir y modificar siendo
* el elemento clave para ver que hace --> modoEditar = true; !!!
*
* */

    private void guardarEmpleado(android.view.View v) {
        String nombre = etNombre.getText().toString().trim();
        String apellidos = etApellidos.getText().toString().trim();
        String rol = etRol.getText().toString().trim().toLowerCase();
        String email = etEmail.getText().toString().trim();
        String telefono = etTelefono.getText().toString().trim();

// Validaciones empezamos con nombre/apellidos
        if (nombre.length()<3 || apellidos.length()<3) {
            Snackbar.make(v, "Nombre/Apelllidos deben tener 3 caracteres min", Snackbar.LENGTH_SHORT).show();
            return;
        }
        //2-  Si el rol no se corresponde mostramos dialog.
        if(!rol.equals("recepcionista") && !rol.equals("mantenimiento") &&
                !rol.equals("limpieza")  ){

           new AlertDialog.Builder(this).setTitle("Error")
            .setMessage("Escriba un rol válido: 'recepcionista', 'mantenimiento' o 'limpieza'")
                    .setPositiveButton("OK", null)
                    .show();
            return;
        }

        // 3- Validamos phone, numeros no letras y 9 dig
        if (!telefono.matches("\\d{9}")) { // exactamente 9 dígitos
            Snackbar.make(v, "Teléfono inválido. Use 9 números.", Snackbar.LENGTH_SHORT).show();
            return;
        }
        // 4- Validar correo electronico
        if ( !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Snackbar.make(v, "Correo electrónico inválido", Snackbar.LENGTH_SHORT).show();
            return;
        }


        if (modoEditar) {
            empleadoActual.setNombre(nombre);
            empleadoActual.setApellidos(apellidos);
            empleadoActual.setRol(rol);
            empleadoActual.setEmail(email);
            empleadoActual.setTelefono(telefono);
            Snackbar.make(v, "Empleado actualizado correctamente", Snackbar.LENGTH_SHORT).show();
        } else {
            Empleado nuevo = new Empleado(nombre, apellidos, rol, email, telefono);
            EmpleadoData.agregarEmpleado(nuevo);
            Snackbar.make(v, "Empleado agregado correctamente", Snackbar.LENGTH_SHORT).show();
        }

        finish(); // vuelvemos a la pantalle anterior
    }


}