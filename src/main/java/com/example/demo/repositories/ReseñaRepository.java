package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.ReseñaModel;

@Repository
public interface ReseñaRepository extends JpaRepository<ReseñaModel, Integer> {
    
    // 🔍 Busca todas las reseñas de un trabajador ordenadas por la más reciente
    List<ReseñaModel> findByTrabajadorIdOrderByFechaCreacionDesc(Integer trabajadorId);
    
    // 🛡️ Verifica si una solicitud ya tiene una reseña creada
    boolean existsBySolicitudId(Integer solicitudId);
}