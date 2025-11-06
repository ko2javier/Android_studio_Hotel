/**
 * Autor: K. Jabier O'Reilly
 * Proyecto: Gestión de Hotel - Práctica 1ª Evaluación (PMDM 2025/2026)
 * Clase: AdaptadorPlanta.java
 * Descripción: Adaptador personalizado para mostrar la información de cada planta del hotel,
 *              incluyendo el número de habitaciones simples, dobles y triples.
 * Centro: C.F.G.S. Desarrollo de Aplicaciones Multiplataforma
 * Módulo: Programación Multimedia y Dispositivos Móviles
 */

package com.example.hotel_hw_1.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hotel_hw_1.R;
import com.example.hotel_hw_1.modelos.Planta;

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

        // Referencio los campos por ID
        ImageView imgIcono = convertView.findViewById(R.id.img_icono);
        TextView txtTitulo = convertView.findViewById(R.id.txt_titulo_planta);

        TextView txtSimplesDisp = convertView.findViewById(R.id.txt_simples_disponibles);
        TextView txtSimplesOcup = convertView.findViewById(R.id.txt_simples_ocupadas);


        TextView txtDoblesDisp = convertView.findViewById(R.id.txt_dobles_disponibles);
        TextView txtDoblesOcup = convertView.findViewById(R.id.txt_dobles_ocupadas);


        TextView txtTriplesDisp = convertView.findViewById(R.id.txt_triples_disponibles);
        TextView txtTriplesOcup = convertView.findViewById(R.id.txt_triples_ocupadas);

        // Asigno el  icono y título
        imgIcono.setImageResource(R.drawable.hotel_bg);
        txtTitulo.setText(planta.getNombre());

        int simplesOcupadas = 40 - planta.getNumSimples();
        int doblesOcupadas = 50 - planta.getNumDobles();
        int triplesOcupadas = 10 - planta.getNumTriples();

        // Asigno los valores!
        txtSimplesDisp.setText(planta.getNumSimples()+"");
        txtSimplesOcup.setText(simplesOcupadas+"");

        txtDoblesDisp.setText(planta.getNumDobles()+"");
        txtDoblesOcup.setText(doblesOcupadas+"");

        txtTriplesDisp.setText(planta.getNumTriples()+"");
        txtTriplesOcup.setText(triplesOcupadas+"");

        return convertView;
    }
}
