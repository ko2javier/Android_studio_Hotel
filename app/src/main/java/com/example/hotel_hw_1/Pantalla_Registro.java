package com.example.hotel_hw_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.example.hotel_hw_1.model.Usuario;
import com.example.hotel_hw_1.model.UsuarioData;
import com.google.android.material.snackbar.Snackbar;

public class Pantalla_Registro extends AppCompatActivity {

    /*
    *
    * valido los campos antes de registrar
    * pongo el foco en el ultimo campo con error
    * y lo señalo
    * */

    private boolean validarCampos(EditText... campos) {
        for (EditText campo : campos) {
            if (campo.getText().toString().trim().isEmpty()) {
                campo.setError("Este campo es obligatorio");
                campo.requestFocus();
                return false;
            }
        }
        return true;
    }
/* Compruebo terminos
* y condiciones y que las
* contraseñas coincidan */
    private static boolean chequar_pass(View v, EditText et_password,
                                        EditText et_confirmPassword, Switch sw_terminos_codi) {
        // validar contraseña
        String pass1 = et_password.getText().toString();
        String pass2 = et_confirmPassword.getText().toString();

        if (!pass1.equals(pass2)) {
            et_confirmPassword.setError("Las contraseñas no coinciden");
            et_confirmPassword.requestFocus();
            return true;
        }

        // validar términos
        if (!sw_terminos_codi.isChecked()) {
            Snackbar.make(v, "Debes aceptar los términos y condiciones", Snackbar.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    /* Añado usuario a la lista despues confirmando asi
    * su registro*/
    private static void Add_User_to_List(EditText et_eamil, EditText et_nombre, EditText et_apellidos, EditText et_phone, EditText et_password) {
        String email = et_eamil.getText().toString();
        String nombre = et_nombre.getText().toString();
        String apellidos = et_apellidos.getText().toString();
        String phone = et_phone.getText().toString();
        String pass1 = et_password.getText().toString();
        Usuario nuevoHuesped = new Usuario(email, pass1, "huesped", apellidos, nombre, phone);

        UsuarioData.addUsuario(nuevoHuesped);
    }



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
/* defino los datos del registro y los paso a nuevo Huesped de confirmar*/
        EditText et_nombre = findViewById(R.id.Campo_DatosNombreUser_PantallaRegistro);
        EditText et_eamil= findViewById(R.id.Campo_email_PantallaRegistro);
        EditText et_apellidos = findViewById(R.id.Campo_DatosApellidosUser_PantallaRegistro);
        EditText et_phone= findViewById(R.id.Campo_telefono_PantallaRegistro);
        Button btn_confirmar= findViewById(R.id.boton_registrate_pantalla_registro);
        Button btn_cancelar= findViewById(R.id.boton_cancelar_pantalla_registro);
        Switch sw_terminos_codi= findViewById(R.id.switch_pantalla_registro);
        EditText et_password= findViewById(R.id.Campo_Contrasena_PantallaRegistro);
        EditText et_confirmPassword = findViewById(R.id.Campo_Confirma_Contrasena_PantallaRegistro);




        // pongo a la escucha es boton cancelar
        btn_cancelar.setOnClickListener(v->{
            Intent intent= new Intent(Pantalla_Registro.this, Pantalla_Inicio.class);
            startActivity(intent);
        });
// pongo el boton confirmar a la escucha si
// todo esta ok registramos si hay fallo lo mostramos

        btn_confirmar.setOnClickListener(v->{
            // validadmos campos
            boolean ok = validarCampos(et_nombre, et_apellidos, et_eamil, et_phone);

            if (chequar_pass(v, et_password, et_confirmPassword, sw_terminos_codi)) return;

            if (ok) {

                Add_User_to_List(et_eamil, et_nombre, et_apellidos, et_phone, et_password);

                // Pasar a la siguiente pantalla
                Intent intent = new Intent(Pantalla_Registro.this, Pantalla_Inicio.class);
                Snackbar.make(v," Exito Usuario Resgitrado", Snackbar.LENGTH_LONG).show();

                startActivity(intent);

            }

        });






    }




}