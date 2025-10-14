package com.example.hotel_hw_1.model;

public class Gest extends Usuario {

    private String nombre;
    private String apellidos;
    private String phone;

    public Gest() {
    }


    public Gest(String email, String pass, String tipo_user, String nombre, String apellidos, String phone) {
        super(email, pass, tipo_user);
        this.apellidos = apellidos;
        this.nombre = nombre;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
}
