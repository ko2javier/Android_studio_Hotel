package com.example.hotel_hw_1;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.example.hotel_hw_1.model.Usuario;

public class Consultar_Editar_Perfil extends AppCompatActivity {

    private static void pasar_ventana_editar_perfil(Usuario usuario, EditText field_email,
                                                    EditText field_password, EditText field_type_user,
                                                    EditText field_nombre, EditText field_apellidos,
                                                    EditText field_phone) {
        if (usuario != null) {
            Log.d("PERFIL_DEBUG", "Cargando perfil de: " + usuario.getEmail());
            // obtengo los datos y habilito los campos que podrá cambiar !!
            field_email.setText(usuario.getEmail());
            field_password.setText(usuario.getPass());
            field_password.setEnabled(true);

            field_type_user.setText(usuario.getTipo_usuario());
            field_nombre.setText(usuario.getNombre());
            field_nombre.setEnabled(true);
            field_apellidos.setText(usuario.getApellidos());
            field_apellidos.setEnabled(true);
            field_phone.setText(usuario.getTelefono());
            field_phone.setEnabled(true);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_consultar_editar_perfil);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.consultar_editar_perfil), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Variables del Layout
        EditText field_nombre= findViewById(R.id.campo_nombre_editar_perfil);
        EditText field_apellidos= findViewById(R.id.campo_apellidos_editar_perfil);
        EditText field_email= findViewById(R.id.campo_email_editar_perfil);
        EditText field_phone= findViewById(R.id.campo_telefono_editar_perfil);
        EditText field_password= findViewById(R.id.campo_pass_editar_perfil);
        EditText field_type_user= findViewById(R.id.campo_tipo_user_editar_perfil);
        Button btn_confirmar_cambios= findViewById(R.id.btn_confirmar_editar_perfil);
        Button btn_volver= findViewById(R.id.btn_volver_editar_perfil);

        // Obtengo Instancia de usuario y paso datos para cargarlos con el metodo

        Usuario usuario = Usuario.getInstance();

        pasar_ventana_editar_perfil(usuario, field_email, field_password, field_type_user, field_nombre, field_apellidos, field_phone);

        btn_volver.setOnClickListener(v->{
            finish();
        });

    }



}