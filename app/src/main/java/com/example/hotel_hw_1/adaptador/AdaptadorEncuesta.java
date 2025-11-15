/**
 * Autor: K. Jabier O'Reilly
 * Proyecto: Gestión de Hotel - Práctica 1ª Evaluación (PMDM 2025/2026)
 * Clase: AdaptadorEncuesta.java
 *
 */

package com.example.hotel_hw_1.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.hotel_hw_1.R;
import com.example.hotel_hw_1.modelo.Encuesta;

import java.util.List;

public class AdaptadorEncuesta extends ArrayAdapter<Encuesta> {

    private final Context context;
    private final List<Encuesta> encuestas;

    public AdaptadorEncuesta(Context context, List<Encuesta> encuestas) {
        super(context, 0, encuestas);
        this.context = context;
        this.encuestas = encuestas;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_encuesta, parent, false);
        }

        Encuesta encuesta = encuestas.get(position);

        // declaro mis variables

        TextView txtCategoria = convertView.findViewById(R.id.txt_categoria_encuesta);
        RatingBar rating = convertView.findViewById(R.id.rating_encuesta);
        TextView txtPromedio = convertView.findViewById(R.id.txt_promedio_encuesta);
        TextView txtCantidad = convertView.findViewById(R.id.txt_cant_encuesta);
        TextView  txt_comentario_general= convertView.findViewById(R.id.txt_comentario_general);

        // les pongo los valores a las mismas con los set!!

        txtCategoria.setText(encuesta.getCategoria());
        rating.setRating(encuesta.getPromedio());
        txtPromedio.setText("Promedio: " + encuesta.getPromedio());
        txtCantidad.setText(encuesta.getCantidad() + " encuestas registradas");
        String comentario;
        switch (encuesta.getCategoria()) {
            case "Limpieza de la habitación":
                comentario = "\"Todo muy bueno, continúen con el trabajo.\"";
                break;
            case "Atención en recepción":
                comentario = "\"Excelente trato, muy amables.\"";
                break;
            case "Servicio de mantenimiento durante su estancia":
                comentario = "\"Respondieron rápido ante los problemas.\"";
                break;
            default:
                comentario = "\"Muy satisfecho con el servicio.\"";
                break;
        }


        txt_comentario_general.setText(comentario);

        return convertView;
    }
}
