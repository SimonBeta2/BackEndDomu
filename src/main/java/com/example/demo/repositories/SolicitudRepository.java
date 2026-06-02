package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.SolicitudModel;

@Repository
public interface SolicitudRepository extends JpaRepository<SolicitudModel, Integer> {
    
    // Para la pestaña "Solicitudes": Ver lo que YO como cliente he pedido
    List<SolicitudModel> findByClienteId(Integer clienteId);

    // Para la pestaña "Recibidas": Ver las solicitudes que le han llegado a MIS ofertas
    // Spring Boot entra a 'oferta', luego a 'usuario' y filtra por su ID automáticamente
    List<SolicitudModel> findByOfertaUsuarioId(Integer trabajadorId);
}