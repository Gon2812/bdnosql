package com.rest.bdnosql;

import java.util.List;

public class Usuario {
    private String correo;
    private String pass;
    private String nombre;
    private String apellido;
    private List<String> rol;

    public List<String> getRol() {
        return rol;
    }

    public void setRol(List<String> rol) {
        this.rol = rol;
    }

    public Usuario(){
    }
    
    public Usuario(String correo, String pass, String nombre, String apellido){
        this.correo = correo;
        this.pass = pass;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getCorreo(){
        return this.correo;
    }

    public void setCorreo(String correo){
        this.correo = correo;
    }

    public String getPass(){
        return this.pass;
    }

    public void setPass(String pass){
        this.pass = pass;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getApellido(){
        return this.apellido;
    }

    public void setApellido(String apellido){
        this.apellido = apellido;
    }
}
