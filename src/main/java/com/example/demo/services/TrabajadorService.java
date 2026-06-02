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

    public void registrarTrabajadorSiNoExiste(UsuarioModel usuario) {
        if (usuario == null) return;

        // Si este usuario no está en la tabla trabajador, lo registramos
        if (trabajadorRepository.findByUsuario(usuario).isEmpty()) {
            TrabajadorModel nuevoTrabajador = new TrabajadorModel(usuario);
            trabajadorRepository.save(nuevoTrabajador);
            System.out.println("🎉 Usuario ID " + usuario.getId() + " registrado en la tabla trabajador.");
        }
    }
}