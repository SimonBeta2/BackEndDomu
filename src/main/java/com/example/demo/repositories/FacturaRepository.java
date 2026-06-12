package com.example.demo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.FacturaModel;

@Repository
public interface FacturaRepository extends JpaRepository<FacturaModel, Integer> {
    
    // 🛡️ Para verificar en el servicio si la solicitud ya fue facturada antes
    boolean existsBySolicitudId(Integer solicitudId);
}
