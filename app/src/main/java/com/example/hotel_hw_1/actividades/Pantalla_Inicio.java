
/**
 * Autor: K. Jabier O'Reilly
 * Proyecto: Gestión de Hotel - Práctica 1ª Evaluación (PMDM 2025/2026)
 * Clase: Pantalla_Inicio.java
 * Descripción: Pantalla principal de inicio de sesión y registro del sistema.
 *              Verifica credenciales y redirige al menú correspondiente según el rol.
 * Centro: C.F.G.S. Desarrollo de Aplicaciones Multiplataforma
 * Módulo: Programación Multimedia y Dispositivos Móviles
 */

package com.example.hotel_hw_1.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotel_hw_1.R;
import com.example.hotel_hw_1.modelos.Usuario;
import com.example.hotel_hw_1.repositorios.UsuarioData;
import com.google.android.material.snackbar.Snackbar;

public class Pantalla_Inicio extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pantalla_inicio);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Pantalla_Inicio), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        /*
        * defino los datos xml,
        * */
        Button btn_iniciar_sesion= findViewById(R.id.boton_inicio_sesion);
        Button btn_registrarse= findViewById(R.id.boton_inicio_registrase);
        EditText et_campo_user= findViewById(R.id.campo_user_name);
        EditText et_campo_password= findViewById(R.id.campo_pasword);

        // creo la instancia de el model usuario.
        Usuario usuario = Usuario.getInstance();


        /*
        * pongo a la escucha el boton iniciar- sesion */

        btn_iniciar_sesion.setOnClickListener(v->{
            /*
            * obtengo los datos que escribio el user email+ psw
            * y se los asigno a la clase Usuario
            * de este modo lo puedo
            * */
            String campo_user= et_campo_user.getText().toString();
            String campo_pass= et_campo_password.getText().toString();

            if (campo_user.isEmpty() || campo_pass.isEmpty()) {
                Snackbar.make(v, "Por favor, introduzca usuario y contraseña", Snackbar.LENGTH_SHORT).show();
                return;
            }

            Usuario u = UsuarioData.checkLogin(campo_user, campo_pass);


            if (u!=null){
                // Guardamos el usuario completo (Empleado o Gest)
                Usuario.setInstance(u);
                Log.d("LOGIN_DEBUG", "Usuario logueado: " + u.getEmail() + " | Tipo: " + u.getTipo_usuario());

                Intent intent = u.obtenerPantalla(this);
                startActivity(intent);
            }else{
                Snackbar.make(v, "Usuario o contraseña incorrectos", Snackbar.LENGTH_SHORT).show();
            }


        });

        btn_registrarse.setOnClickListener(v->{
            Intent intent = new Intent(Pantalla_Inicio.this, Pantalla_Registro.class);
            startActivity(intent);

        });

    }
}