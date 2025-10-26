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
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.hotel_hw_1.R;

import java.util.List;

public class AdaptadorEncuestaEditable extends ArrayAdapter<Encuesta> {
    private final Context context;
    private final List<Encuesta> encuestas;
    private String comentario = "";

    public AdaptadorEncuestaEditable(Context context, List<Encuesta> encuestas) {
        super(context, 0, encuestas);
        this.context = context;
        this.encuestas = encuestas;
    }

    @Override
    public int getCount() {
        // sumamos 1 al tamaño total por el ítem de comentarios
        return encuestas.size() + 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);

        // Último ítem → el campo de comentarios
        if (position == encuestas.size()) {
            convertView = inflater.inflate(R.layout.item_encuesta_comentario, parent, false);
            EditText etComentario = convertView.findViewById(R.id.et_comentario_item);

            // guardamos el texto a medida que el usuario escribe
            etComentario.setText(comentario);
            etComentario.setOnFocusChangeListener((v, hasFocus) -> {
                if (!hasFocus) comentario = etComentario.getText().toString();
            });

            return convertView;
        }

        // Ítems normales (preguntas)
        if (convertView == null || convertView.findViewById(R.id.rating_encuesta) == null) {
            convertView = inflater.inflate(R.layout.item_encuesta_editable, parent, false);
        }

        Encuesta encuesta = encuestas.get(position);

        TextView txtCategoria = convertView.findViewById(R.id.txt_categoria_encuesta);
        RatingBar ratingBar = convertView.findViewById(R.id.rating_encuesta);

        txtCategoria.setText(encuesta.getCategoria());
        ratingBar.setRating(encuesta.getPromedio());

        ratingBar.setOnRatingBarChangeListener((bar, rating, fromUser) -> {
            if (fromUser) encuesta.setPromedio(rating);
        });

        return convertView;
    }

    public List<Encuesta> getResultados() {
        return encuestas;
    }

    public String getComentario() {
        return comentario;
    }
}
