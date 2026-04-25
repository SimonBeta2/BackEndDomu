package com.example.demo.dtos;

import lombok.NoArgsConstructor;


@NoArgsConstructor
public class AuthResponseDTO {
    private String token;
    private UsuarioDTO user;

    public AuthResponseDTO(String token, UsuarioDTO user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UsuarioDTO getUser() {
        return user;
    }

    public void setUser(UsuarioDTO user) {
        this.user = user;
    }
}