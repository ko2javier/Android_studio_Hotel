/**
 * Autor: K. Jabier O'Reilly
 * Proyecto: Gestión de Hotel - Práctica 1ª Evaluación (PMDM 2025/2026)
 * Clase: EmpleadoAdapter.java
 * Descripción: Adaptador personalizado que gestiona la visualización de empleados
 *              en una lista, permitiendo editar o eliminar registros mediante
 *              botones asociados a cada elemento.
 * Centro: C.F.G.S. Desarrollo de Aplicaciones Multiplataforma
 * Módulo: Programación Multimedia y Dispositivos Móviles
 */

package com.example.hotel_hw_1.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.ArrayAdapter;

import com.example.hotel_hw_1.R;
import com.example.hotel_hw_1.model.Empleado;

import java.util.List;

public class EmpleadoAdapter extends ArrayAdapter<Empleado> {

    private final Context context;
    private final List<Empleado> listaEmpleados;

    public interface OnEmpleadoActionListener {
        void onEditar(Empleado empleado, int position);
        void onEliminar(Empleado empleado, int position);
    }

    private final OnEmpleadoActionListener listener;

    public EmpleadoAdapter(@NonNull Context context, List<Empleado> listaEmpleados, OnEmpleadoActionListener listener) {
        super(context, 0, listaEmpleados);
        this.context = context;
        this.listaEmpleados = listaEmpleados;
        this.listener = listener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_empleado, parent, false);
        }

        Empleado empleado = listaEmpleados.get(position);

        TextView txtNombre = convertView.findViewById(R.id.txt_nombre_empleado);
        TextView txtRol = convertView.findViewById(R.id.txt_rol_empleado);
        Button btnEditar = convertView.findViewById(R.id.btn_editar_empleado);
        Button btnEliminar = convertView.findViewById(R.id.btn_eliminar_empleado);

        txtNombre.setText(empleado.getNombre() + " " + empleado.getApellidos());
        txtRol.setText("Rol: " + empleado.getRol());

        btnEditar.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEditar(empleado, position);
            }
        });

        btnEliminar.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEliminar(empleado, position);
            }
        });

        return convertView;
    }

    // Refrescar la lista cuando haya cambios (añadir, eliminar o editar)
    public void actualizarDatos() {
        notifyDataSetChanged();
    }
}
