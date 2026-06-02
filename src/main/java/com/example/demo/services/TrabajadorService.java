package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.TrabajadorModel;
import com.example.demo.models.UsuarioModel;
import com.example.demo.repositories.TrabajadorRepository;

@Service
public class TrabajadorService {

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    public TrabajadorModel registrarTrabajadorSiNoExiste(UsuarioModel usuario) {
    if (usuario == null) return null;

    // Buscamos si ya existe
    return trabajadorRepository.findByUsuario(usuario)
        .orElseGet(() -> {
            // Si no existe, lo crea, lo guarda y lo retorna
            TrabajadorModel nuevoTrabajador = new TrabajadorModel(usuario);
            return trabajadorRepository.save(nuevoTrabajador);
        });
}
}