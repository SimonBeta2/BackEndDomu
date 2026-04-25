package com.example.demo.dtos;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UsuarioDTO {
    private Integer id;
    private String email;
    private String nombre;
    private String telefono;

    public UsuarioDTO(Integer id, String email, String nombre, String telefono) {
    this.id = id;
    this.email = email;
    this.nombre = nombre;
    this.telefono = telefono;
}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}