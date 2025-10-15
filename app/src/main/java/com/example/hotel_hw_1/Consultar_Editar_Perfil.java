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

import com.example.hotel_hw_1.model.Empleado;
import com.example.hotel_hw_1.model.Gest;
import com.example.hotel_hw_1.model.Usuario;

public class Consultar_Editar_Perfil extends AppCompatActivity {

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

        // defino mi bundle recibido

        Usuario usuario = Usuario.getInstance();

        if (usuario != null) {
            Log.d("PERFIL_DEBUG", "Cargando perfil de: " + usuario.getEmail());

            field_email.setText(usuario.getEmail());
            field_password.setText(usuario.getPass());
            field_type_user.setText(usuario.getTipo_user());

            if (usuario instanceof Empleado) {
                Empleado e = (Empleado) usuario;
                field_nombre.setText(e.getNombre());
                field_apellidos.setText(e.getApellidos());
                field_phone.setText(e.getPhone());
            } else if (usuario instanceof Gest) {
                Gest g = (Gest) usuario;
                field_nombre.setText(g.getNombre());
                field_apellidos.setText(g.getApellidos());
                field_phone.setText(g.getPhone());
            }
        } else {
            Log.e("PERFIL_DEBUG", "⚠ No hay usuario cargado en memoria");
        }







    }

}