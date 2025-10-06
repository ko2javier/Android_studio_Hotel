package com.example.hotel_hw_1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotel_hw_1.model.Usuario;
import com.example.hotel_hw_1.model.UsuarioData;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

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
            Usuario u = UsuarioData.checkLogin(campo_user, campo_pass);

            if (u!=null){
                usuario.setEmail(campo_user);
                usuario.setPass(campo_pass);
                usuario.setTipo_user(u.getTipo_user());
                Intent intent= new Intent(Pantalla_Inicio.this, Pantalla_Menu_Usuarios.class);

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