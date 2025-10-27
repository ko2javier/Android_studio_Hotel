/**
 * Autor: K. Jabier O'Reilly
 * Proyecto: Gestión de Hotel - Práctica 1ª Evaluación (PMDM 2025/2026)
 * Clase: GestionEmpleadosActivity.java
 * Descripción: Permite al gerente ver, añadir, editar o eliminar empleados del hotel.
 *              El listado se actualiza automáticamente al volver desde el formulario.
 * Centro: C.F.G.S. Desarrollo de Aplicaciones Multiplataforma
 * Módulo: Programación Multimedia y Dispositivos Móviles
 */

package com.example.hotel_hw_1.actividades;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


import com.example.hotel_hw_1.R;
import com.example.hotel_hw_1.model.Empleado;
import com.example.hotel_hw_1.adaptadores.EmpleadoAdapter;
import com.example.hotel_hw_1.repositorios.EmpleadoData;

import java.util.List;

public class GestionEmpleadosActivity extends AppCompatActivity {

    private ListView listViewEmpleados;
    private Button btnAgregar;
    private EmpleadoAdapter adapter;
    private List<Empleado> listaEmpleados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gestion_empleados);

        // defino mis variables
        listViewEmpleados = findViewById(R.id.lista_empleados);
        btnAgregar = findViewById(R.id.btn_agregar_empleado);
        Button btn_volver_gestion_empleados= findViewById(R.id.btn_volver_gestion_empleados);

        // defino la lista de empleados que utilizare en el adapter
        listaEmpleados = EmpleadoData.getEmpleados();

        adapter = new EmpleadoAdapter(this, listaEmpleados, new EmpleadoAdapter.OnEmpleadoActionListener() {
            @Override
            // Vamos a la pantalla Form Empleado y pasamos la pos del empleado en EmpleadoData!!
            public void onEditar(Empleado empleado, int position) {
                Intent intent = new Intent(GestionEmpleadosActivity.this,
                        EmpleadoFormActivity.class);
                intent.putExtra("posicion", position);
                startActivity(intent);
            }

            @Override
            public void onEliminar(Empleado empleado, int position) {
                mostrarDialogoEliminar(empleado, position);
            }
        });

        listViewEmpleados.setAdapter(adapter);

        // declaro los listeners
        btnAgregar.setOnClickListener(v->{
            startActivity(new Intent(this, EmpleadoFormActivity.class));

        });
        btn_volver_gestion_empleados.setOnClickListener(v->{
            finish();
        });
    }
/* Este metodo es fundamental
* con ello logro que se recargue el listado despues de
* modificar o agregar un valor en la lista de empleado
* tiene contrapartida en el adaptador de empleado!! , sin ello
* no funciona!!
* */
    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.actualizarDatos();
        }
    }





    private void mostrarDialogoEliminar(Empleado empleado, int position) {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar empleado")
                .setMessage("¿Seguro que desea eliminar a " + empleado.getNombre() + "?")
                .setPositiveButton("Sí", (dialog, which) -> {
                    EmpleadoData.eliminarEmpleado(empleado);
                    adapter.actualizarDatos();
                })
                .setNegativeButton("No", null)
                .show();
    }
}
