package com.example.hotel_hw_1;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotel_hw_1.model.Usuario;

public class Pantalla_Menu_Usuarios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Recupero el usuario logeado
        Usuario usuario = Usuario.getInstance();
        //String tipoUser = usuario.getTipo_user();

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_pantalla_menu_usuarios);

        // Switch para cargar el layout correspondiente
        /*
        switch (tipoUser) {
            case "gerente":
                setContentView(R.layout.activity_menu_gerente);
                break;

            case "recepcionista":
                setContentView(R.layout.activity_menu_recepcionista);
                break;

            case "limpiador":
                setContentView(R.layout.activity_menu_limpiador);
                break;

            case "mantenimiento":
                setContentView(R.layout.activity_menu_mantenimiento);
                break;

            case "huesped":
                setContentView(R.layout.activity_menu_huesped);
                break;

          }
    }*/


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}