package com.example.hotel_hw_1.model;

import android.content.Context;
import android.content.Intent;

import com.example.hotel_hw_1.GerenteActivity;
import com.example.hotel_hw_1.HuespedActivity;
import com.example.hotel_hw_1.LimpiadorActivity;
import com.example.hotel_hw_1.MantenimientoActivity;
import com.example.hotel_hw_1.Pantalla_Menu_Usuarios;
import com.example.hotel_hw_1.RecepcionistaActivity;

import java.io.Serializable;

public class Usuario implements Serializable {
    private static Usuario instance;
     private String tipo_user;
     private  String email; // user

    private  String pass ;

    public static Usuario getInstance() {
        if (instance == null) {
            instance = new Usuario();
        }
        return instance;
    }

   // Método para guardar el usuario actual (Empleado o Gest)
    public static void setInstance(Usuario u) {
        instance = u;
    }
      public Usuario() {}

    // Constructor completo
    public Usuario(String email, String pass, String tipo_user) {

        this.email = email;
        this.pass = pass;
        this.tipo_user = tipo_user;
    }

    public String getEmail() {return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPass() { return pass; }

    public void setPass(String pass) {this.pass = pass; }

    public String getTipo_user() { return tipo_user; }


    // Método para limpiar el usuario actual (logout)
    public  void limpiarInstance() {
        instance = null;
    }

    public void setTipo_user(String tipo_user) { this.tipo_user = tipo_user; }

    /*Creo el metodo para llamar a las diferentes pantallas respecto
    a los posibles usuarios digase
    huesped
    gerente
    mtto
    recep
    limpieza

*/
    public Intent obtenerPantalla(Context context) {
        if (tipo_user == null) {
            return null;
        }

        switch (tipo_user.toLowerCase()) {
            case "gerente":
                return new Intent(context, GerenteActivity.class);
            case "recepcionista":
                return new Intent(context, RecepcionistaActivity.class);
            case "limpieza":
                return new Intent(context, LimpiadorActivity.class);
            case "mantenimiento":
                return new Intent(context, MantenimientoActivity.class);
            case "huesped":
                return new Intent(context, HuespedActivity.class);
            default:
                return null;
        }
    }



}

