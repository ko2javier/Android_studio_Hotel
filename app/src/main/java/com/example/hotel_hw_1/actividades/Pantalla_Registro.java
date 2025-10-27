/**
 * Autor: K. Jabier O'Reilly
 * Proyecto: Gestión de Hotel - Práctica 1ª Evaluación (PMDM 2025/2026)
 * Clase: Pantalla_Registro.java
 * Descripción: Pantalla de registro de nuevos usuarios (huéspedes).
 *              Incluye validaciones de todos los campos y control del switch de términos.
 * Centro: C.F.G.S. Desarrollo de Aplicaciones Multiplataforma
 * Módulo: Programación Multimedia y Dispositivos Móviles
 */

package com.example.hotel_hw_1.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotel_hw_1.R;
import com.example.hotel_hw_1.model.Usuario;
import com.example.hotel_hw_1.ropositorios.UsuarioData;
import com.example.hotel_hw_1.model.Validaciones;
import com.google.android.material.snackbar.Snackbar;

public class Pantalla_Registro extends AppCompatActivity {
    private EditText et_nombre,et_apellidos,et_email, et_phone, et_password,et_confirmPassword;
    private  Button btn_registrar, btn_cancelar; private Switch sw_terminos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pantalla_registro);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainPantallaRegistro), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Identificamos varibles con id !!
         et_nombre = findViewById(R.id.Campo_DatosNombreUser_PantallaRegistro);
         et_apellidos = findViewById(R.id.Campo_DatosApellidosUser_PantallaRegistro);
         et_email = findViewById(R.id.Campo_email_PantallaRegistro);
         et_phone = findViewById(R.id.Campo_telefono_PantallaRegistro);
         et_password = findViewById(R.id.Campo_Contrasena_PantallaRegistro);
         et_confirmPassword = findViewById(R.id.Campo_Confirma_Contrasena_PantallaRegistro);
         sw_terminos = findViewById(R.id.switch_pantalla_registro);
         btn_registrar = findViewById(R.id.boton_registrate_pantalla_registro);
         btn_cancelar = findViewById(R.id.boton_cancelar_pantalla_registro);

         // Defino listeners
        // Botón cancelar
        btn_cancelar.setOnClickListener(v -> {
            Intent intent = new Intent(Pantalla_Registro.this, Pantalla_Inicio.class);
            startActivity(intent);
        });

        // Botón registrar
        btn_registrar.setOnClickListener(v -> {
            if (validar_campos(v)) return;

            registrar_user(v);
        });
    }

    private void registrar_user(View v) {
        // Si todo está correcto registramos al usuario
        Usuario nuevo = new Usuario(
                et_email.getText().toString().trim(),
                et_password.getText().toString().trim(),
                "huesped",
                et_apellidos.getText().toString().trim(),
                et_nombre.getText().toString().trim(),
                et_phone.getText().toString().trim()
        );

        UsuarioData.addUsuario(nuevo);

        Snackbar.make(v, " Usuario registrado con éxito", Snackbar.LENGTH_LONG).show();

        // Vuelve al inicio tras breve pausa
        v.postDelayed(() -> {
            Intent intent = new Intent(Pantalla_Registro.this, Pantalla_Inicio.class);
            startActivity(intent);
            finish();
        }, 1500);
    }

    private boolean validar_campos(View v) {
        // Validaciones secuenciales,  usando la clase Validaciones
        if (!Validaciones.validarCampoNoVacio(v, et_nombre, "Debe ingresar su nombre")) return true;
        if (!Validaciones.validarNombreOApellido(v, et_nombre, "El nombre")) return true;

        if (!Validaciones.validarCampoNoVacio(v, et_apellidos, "Debe ingresar sus apellidos"))
            return true;
        if (!Validaciones.validarNombreOApellido(v, et_apellidos, "Los apellidos")) return true;

        if (!Validaciones.validarEmail(v, et_email)) return true;
        if (!Validaciones.validarTelefono(v, et_phone)) return true;
        if (!Validaciones.validarPassword(v, et_password)) return true;
        if (!Validaciones.validarConfirmacionPassword(v, et_password, et_confirmPassword))
            return true;

        if (!sw_terminos.isChecked()) {
            Snackbar.make(v, "Debe aceptar los términos y condiciones", Snackbar.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}
