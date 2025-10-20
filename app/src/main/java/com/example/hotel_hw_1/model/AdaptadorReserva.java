package com.example.hotel_hw_1.model;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hotel_hw_1.R;

import java.util.List;

public class AdaptadorReserva extends ArrayAdapter<String> {

    private final Context context;
    private final List<String> reservas;

    public AdaptadorReserva(Context context, List<String> reservas) {
        super(context, 0, reservas);
        this.context = context;
        this.reservas = reservas;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_reserva, parent, false);
        }

        String reserva = reservas.get(position);

        TextView txtDetalle = convertView.findViewById(R.id.txt_detalle_reserva);
        txtDetalle.setText(reserva);

        // Colores dinámicos según estado
        if (reserva.toLowerCase().contains("cancelada")) {
            txtDetalle.setTextColor(context.getColor(android.R.color.holo_red_dark));
        } else {
            txtDetalle.setTextColor(context.getColor(android.R.color.holo_green_dark));
        }

        return convertView;
    }
}
