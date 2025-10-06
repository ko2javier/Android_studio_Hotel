package com.example.hotel_hw_1.model;

public class Usuario {
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

    public void setTipo_user(String tipo_user) { this.tipo_user = tipo_user; }


}

