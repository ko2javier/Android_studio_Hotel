package com.example.hotel_hw_1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotel_hw_1.model.Tarea;
import com.example.hotel_hw_1.model.TareaAdapter;
import com.example.hotel_hw_1.model.TareaData;
import com.example.hotel_hw_1.model.Usuario;

import java.util.List;

public class GestionTareasActivity extends AppCompatActivity {

    private ListView listViewTareas;
    private TextView txtTitulo;
    private Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gestion_tareas);

        listViewTareas = findViewById(R.id.listview_tareas);
        txtTitulo = findViewById(R.id.txt_titulo_tareas);
        btnVolver = findViewById(R.id.btn_volver_tareas);

        // Obtenemos usuario logueado
        Usuario usuario = Usuario.getInstance();
        String rol = usuario.getTipo_usuario();

        // Dependiendo del rol, filtramos las tareas
        List<Tarea> listaTareas;
        if (rol.equalsIgnoreCase("gerente")) {
            txtTitulo.setText("Tareas de mantenimiento y limpieza");
            listaTareas = TareaData.getTareas();
        } else if (rol.equalsIgnoreCase("mantenimiento")) {
            txtTitulo.setText("Tareas de mantenimiento disponibles");
            listaTareas = TareaData.getTareasPorTipo("Mantenimiento");
        } else if (rol.equalsIgnoreCase("limpieza")) {
            txtTitulo.setText("Tareas de limpieza disponibles");
            listaTareas = TareaData.getTareasPorTipo("Limpieza");
        } else {
            txtTitulo.setText("Sin tareas disponibles");
            listaTareas = null;
        }

        // Si hay tareas, las mostramos
        if (listaTareas != null && !listaTareas.isEmpty()) {
            TareaAdapter adapter = new TareaAdapter(this, listaTareas, rol);
            listViewTareas.setAdapter(adapter);
        } else {
            txtTitulo.setText(txtTitulo.getText() + "\n(No hay tareas registradas)");
        }

        btnVolver.setOnClickListener(v -> finish());
    }
}