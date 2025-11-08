/**
 * Autor: K. Jabier O'Reilly
 * Proyecto: Gestión de Hotel - Práctica 1ª Evaluación (PMDM 2025/2026)
 * Clase: AdapterHuesped.java
 *
 */

package com.example.hotel_hw_1.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.hotel_hw_1.R;
import com.example.hotel_hw_1.modelo.Huesped;
import java.util.List;

public class AdapterHuesped extends BaseAdapter {

    private final Context context;
    private final List<Huesped> listaHuespedes;

    public AdapterHuesped(Context context, List<Huesped> listaHuespedes) {
        this.context = context;
        this.listaHuespedes = listaHuespedes;
    }

    @Override
    public int getCount() {
        return listaHuespedes.size();
    }

    @Override
    public Object getItem(int position) {
        return listaHuespedes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_huesped, parent, false);
        }

        Huesped h = listaHuespedes.get(position);

        TextView txtNombre = convertView.findViewById(R.id.txt_nombre_huesped);
        TextView txtTelefono = convertView.findViewById(R.id.txt_telefono_huesped);
        TextView txtHabitacion = convertView.findViewById(R.id.txt_habitacion_huesped);
        TextView txtEstado = convertView.findViewById(R.id.txt_estado_huesped);

        txtNombre.setText(h.getNombre() + " " + h.getApellidos());
        txtTelefono.setText("Teléfono: " + h.getTelefono());
        txtHabitacion.setText("Habitación: " + h.getHabitacion());

        if (h.isCheckInActivo()) {
            txtEstado.setText("Estado: Check-In");
            txtEstado.setTextColor(context.getResources().getColor(android.R.color.holo_green_dark));
        } else {
            txtEstado.setText("Estado: Check-Out");
            txtEstado.setTextColor(context.getResources().getColor(android.R.color.holo_red_dark));
        }

        return convertView;
    }
}
