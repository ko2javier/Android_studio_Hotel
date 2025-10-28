/**
 * Autor: K. Jabier O'Reilly
 *
 */

package com.example.hotel_hw_1.actividades;

import android.app.AlertDialog;
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
import com.example.hotel_hw_1.modelos.Usuario;
import com.example.hotel_hw_1.repositorios.UsuarioData;
import com.example.hotel_hw_1.modelos.Validaciones;
import com.google.android.material.snackbar.Snackbar;

public class Pantalla_Registro extends AppCompatActivity {
    private EditText et_nombre,et_apellidos,et_email, et_phone, et_password,et_confirmPassword;
    private  Button btn_registrar, btn_cancelar; private Switch sw_terminos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pantalla_registro);


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
            if (!validar_campos(v)) return;

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

    /* metodo para validar todos los campos del registro
    * en este caaso el usuario validado podra entrar al sistema como huesped despues!!
    * */
    private boolean validar_campos(View v) {
        int errores = 0;
        StringBuilder msg = new StringBuilder();

        // Paso 1:  Nombre
        if (!Validaciones.validarNombre(et_nombre)) {
            errores++;
            msg.append("• Nombre inválido.\n");
        }

        // Paso 2: Apellidos
        if (!Validaciones.validarApellidos(et_apellidos)) {
            errores++;
            msg.append("• Apellidos inválidos.\n");
        }

        // Paso 3: Email
        if (!Validaciones.validarEmail(v, et_email)) {
            errores++;
            msg.append("• Email inválido.\n");
        }

        // Paso 4: Teléfono
        if (!Validaciones.validarTelefonoNuevo(et_phone)) {
            errores++;
            msg.append("• Teléfono inválido (9 dígitos).\n");
        }

        // Paso 5: Contraseña
        if (!Validaciones.validarPassword(v, et_password)) {
            errores++;
            msg.append("• La contraseña debe tener al menos 4 caracteres.\n");
        }

        // Paso 6: Confirmación de contraseña
        if (!Validaciones.validarConfirmacionPassword(v, et_password, et_confirmPassword)) {
            errores++;
            msg.append("• Las contraseñas no coinciden.\n");
        }

        // Paso 7: Términos y condiciones
        if (!Validaciones.validarTerminos(sw_terminos, this)) {
            errores++;
            msg.append("• Debe aceptar los términos y condiciones.\n");
        }

        // Paso 8: Si hay errores, mostrar todos juntos
        if (errores > 0) {
            new AlertDialog.Builder(this)
                    .setTitle("Errores en el formulario")
                    .setMessage(msg.toString())
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton("Aceptar", null)
                    .show();
            return false; // Retornamos False porque hay errores
        }

        return true;
    }

}
