package com.example.hotel_hw_1.model;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hotel_hw_1.R;

import java.util.List;

public class TareaAdapter extends ArrayAdapter<Tarea> {

    private Context context;
    private List<Tarea> listaTareas;
    private String rolUsuario;

    public TareaAdapter(@NonNull Context context, List<Tarea> listaTareas, String rolUsuario) {
        super(context, 0, listaTareas);
        this.context = context;
        this.listaTareas = listaTareas;
        this.rolUsuario = rolUsuario;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_tarea, parent, false);
        }

        Tarea tarea = listaTareas.get(position);

        TextView txtTipo = convertView.findViewById(R.id.txt_tipo_tarea);
        TextView txtEstado = convertView.findViewById(R.id.txt_estado_tarea);
        TextView txtUbicacion = convertView.findViewById(R.id.txt_ubicacion_tarea);
        TextView txtDetalle = convertView.findViewById(R.id.txt_detalle_tarea);
        TextView txtAsignada = convertView.findViewById(R.id.txt_asignada_a);
        Button btnAccion = convertView.findViewById(R.id.btn_accion_tarea);

        txtTipo.setText("Tipo: " + tarea.getTipoTarea());
        txtEstado.setText(tarea.getEstado());
        txtUbicacion.setText("Planta " + tarea.getPlanta() + " - Habitación " + tarea.getHabitacion());
        txtDetalle.setText(tarea.getZona() + " - " + tarea.getPasillo());
        txtAsignada.setText("Asignada a: " + tarea.getAsignadaA());

        if (tarea.getEstado().equalsIgnoreCase("Pendiente")) {
            txtEstado.setTextColor(context.getColor(android.R.color.holo_red_dark));
        } else {
            txtEstado.setTextColor(context.getColor(android.R.color.holo_green_dark));
        }

        if (rolUsuario.equalsIgnoreCase("Gerente")) {
            btnAccion.setText("ASIGNAR");
            btnAccion.setOnClickListener(view -> asignarTarea(tarea));
        } else {
            btnAccion.setText("AUTOASIGNAR");
            btnAccion.setOnClickListener(view -> autoasignarTarea(tarea));
        }

        return convertView;
    }

    private void asignarTarea(Tarea tarea) {
        String tipo = tarea.getTipoTarea();
        String empleado = tipo.equalsIgnoreCase("Limpieza") ? "Empleado Limpieza" : "Empleado Mantenimiento";
        int limite = tipo.equalsIgnoreCase("Limpieza") ? 4 : 3;

        // Comprobamos límite
        if (!TareaData.puedeAutoAsignarse(empleado, tipo, limite)) {
            mostrarDialogo("Límite alcanzado", "El empleado ya tiene el número máximo de tareas.");
            return;
        }

        // Confirmación sencilla
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Confirmar asignación");
        builder.setMessage("¿Asignar tarea a " + empleado + "?");
        builder.setPositiveButton("Sí", (dialog, which) -> {
            tarea.setAsignadaA(empleado);
            tarea.setEstado("Asignada");
            notifyDataSetChanged();
            mostrarDialogo("Éxito", "Tarea asignada correctamente.");
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }

    private void autoasignarTarea(Tarea tarea) {
        if (!tarea.getAsignadaA().equalsIgnoreCase("Sin asignar")) {
            mostrarDialogo("Error", "Esta tarea ya está asignada.");
            return;
        }

        String empleado = rolUsuario.equalsIgnoreCase("Limpieza") ? "Empleado Limpieza" : "Empleado Mantenimiento";
        int limite = rolUsuario.equalsIgnoreCase("Limpieza") ? 4 : 3;

        if (!TareaData.puedeAutoAsignarse(empleado, rolUsuario, limite)) {
            mostrarDialogo("Límite alcanzado", "Ya tienes el número máximo de tareas permitidas.");
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Confirmar autoasignación");
        builder.setMessage("¿Deseas autoasignarte esta tarea?");
        builder.setPositiveButton("Sí", (dialog, which) -> {
            tarea.setAsignadaA(empleado);
            tarea.setEstado("Asignada");
            notifyDataSetChanged();
            mostrarDialogo("Éxito", "Tarea autoasignada correctamente.");
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }

    private void mostrarDialogo(String titulo, String mensaje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titulo);
        builder.setMessage(mensaje);
        builder.setPositiveButton("Aceptar", null);
        builder.show();
    }
}

