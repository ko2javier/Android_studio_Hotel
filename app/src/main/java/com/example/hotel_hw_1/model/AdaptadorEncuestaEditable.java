/*
 * Autor: K. Jabier O'Reilly
 * Proyecto: Gestión de Hotel - Práctica 1ª Evaluación (PMDM 2025/2026)
 * Clase: AdaptadorEncuestaEditable.java
 * Descripción: Adaptador para permitir al huésped realizar una encuesta de satisfacción
 *              seleccionando una valoración de 1 a 5 estrellas por categoría.
 */

package com.example.hotel_hw_1.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.hotel_hw_1.R;

import java.util.List;

public class AdaptadorEncuestaEditable extends ArrayAdapter<Encuesta> {

    private final List<Encuesta> encuestas;
    private final Context context;

    public AdaptadorEncuestaEditable(Context context, List<Encuesta> encuestas) {
        super(context, 0, encuestas);
        this.context = context;
        this.encuestas = encuestas;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_encuesta_editable, parent, false);
        }

        Encuesta encuesta = encuestas.get(position);

        TextView txtCategoria = convertView.findViewById(R.id.txt_categoria_encuesta);
        RatingBar ratingBar = convertView.findViewById(R.id.rating_encuesta);

        txtCategoria.setText(encuesta.getCategoria());
        ratingBar.setRating(encuesta.getPromedio());

        // Permitir que el usuario cambie el valor y se guarde en el modelo
        ratingBar.setOnRatingBarChangeListener((bar, rating, fromUser) -> {
            if (fromUser) encuesta.setPromedio(rating);
        });

        return convertView;
    }

    // Método para obtener los valores seleccionados (por ejemplo, al pulsar "Enviar encuesta")
    public List<Encuesta> getResultados() {
        return encuestas;
    }
}
