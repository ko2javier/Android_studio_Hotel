/**
 * Autor: K. Jabier O'Reilly
 * Proyecto: Gestión de Hotel - Práctica 1ª Evaluación (PMDM 2025/2026)
 * Clase: AdaptadorPlanta.java
 * Descripción: Adaptador personalizado para mostrar la información de cada planta del hotel,
 *              incluyendo el número de habitaciones simples, dobles y triples.
 * Centro: C.F.G.S. Desarrollo de Aplicaciones Multiplataforma
 * Módulo: Programación Multimedia y Dispositivos Móviles
 */

package com.example.hotel_hw_1.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hotel_hw_1.R;

import java.util.List;


public class AdaptadorPlanta extends ArrayAdapter<Planta> {

    public AdaptadorPlanta(Context context, List<Planta> plantas) {
        super(context, 0, plantas);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Planta planta = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_planta, parent, false);
        }

        // Referencias a los componentes del layout
        ImageView imgIcono = convertView.findViewById(R.id.img_icono);
        TextView txtTitulo = convertView.findViewById(R.id.txt_titulo_planta);
        TextView txtDetalles = convertView.findViewById(R.id.txt_detalles_planta);

        // Icono fijo (hotel)
        imgIcono.setImageResource(R.drawable.hotel_bg);

        // Texto de la planta

        txtTitulo.setText(planta.getNombre() );

        // Detalles: simples, dobles, triples
        txtDetalles.setText(
                "Simples: " + planta.getNumSimples()  +
                        "\nDobles: " + planta.getNumDobles() +
                        "\nTriples: " + planta.getNumTriples()
        );

        return convertView;
    }
}
