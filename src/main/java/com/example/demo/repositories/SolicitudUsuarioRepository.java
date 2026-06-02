package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.SolicitudUsuarioModel;

@Repository
public interface SolicitudUsuarioRepository extends JpaRepository<SolicitudUsuarioModel, Integer> {
}
