package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.ResenaModel;

@Repository
public interface ResenaRepository extends JpaRepository<ResenaModel, Integer> {
    
    // Traer reseñas de un trabajador específico ordenadas de la más nueva a la más vieja
    List<ResenaModel> findByTrabajadorIdOrderByFechaCreacionDesc(Integer trabajadorId);
    
    // Saber si una solicitud ya fue calificada
    boolean existsBySolicitudId(Integer solicitudId);
}