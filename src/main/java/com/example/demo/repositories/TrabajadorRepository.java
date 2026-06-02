package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.TrabajadorModel;
import com.example.demo.models.UsuarioModel;

@Repository
public interface TrabajadorRepository extends JpaRepository<TrabajadorModel, Integer> {
    // Verifica si el usuario ya está registrado como trabajador usando el objeto Usuario
    Optional<TrabajadorModel> findByUsuario(UsuarioModel usuario);
}
