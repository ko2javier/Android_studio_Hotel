package com.example.hotel_hw_1.model;



public class Empleado extends Usuario {
    private String nombre;
    private String apellidos;
    private String  phone;
    private String tipo_empleado;


    public Empleado(){}

    public Empleado(String nombre, String phone, String apellidos, String tipo_empleado) {
        this.nombre = nombre;
        this.phone = phone;
        this.apellidos = apellidos;
        this.tipo_empleado= tipo_empleado;
    }

    public Empleado(String email, String pass, String tipo_user, String nombre,
                    String phone, String apellidos, String tipo_empleado) {
        super(email, pass, tipo_user);
        this.nombre = nombre;
        this.phone = phone;
        this.apellidos = apellidos;
        this.tipo_empleado= tipo_empleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }


}
