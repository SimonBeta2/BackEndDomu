package com.example.demo.dtos;

import java.util.List;

import com.example.demo.models.DireccionModel;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UsuarioDTO {
    private Integer id;
    private String email;
    private String name;
    private String telefono;
    private String pictureUrl;
    private List<DireccionModel> direcciones;
    
    public UsuarioDTO(Integer id, String email, String name, String telefono, String pictureUrl, List<DireccionModel> direcciones) {
    this.id = id;
    this.email = email;
    this.name = name;
    this.telefono = telefono;
    this.pictureUrl = pictureUrl;
    this.direcciones = direcciones;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public List<DireccionModel> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<DireccionModel> direcciones) {
        this.direcciones = direcciones;
    }
}