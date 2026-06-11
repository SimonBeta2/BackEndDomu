package com.example.demo.services;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.ReseñaModel;
import com.example.demo.models.SolicitudModel;
import com.example.demo.repositories.ReseñaRepository;
import com.example.demo.repositories.SolicitudRepository;

@Service
public class ReseñaService {

    private final ReseñaRepository reseñaRepository;
    private final SolicitudRepository solicitudRepository; // Para validar la solicitud

    public ReseñaService(ReseñaRepository reseñaRepository, SolicitudRepository solicitudRepository) {
        this.reseñaRepository = reseñaRepository;
        this.solicitudRepository = solicitudRepository;
    }

    @Transactional
    public ReseñaModel crearReseña(Integer solicitudId, ReseñaModel reseñaData) {
        // 1. Validar que la solicitud exista
        SolicitudModel solicitud = solicitudRepository.findById(solicitudId)
            .orElseThrow(() -> new RuntimeException("La solicitud con ID " + solicitudId + " no existe."));

        // 2. Validar que no tenga una reseña previa (Relación 1 a 1)
        if (reseñaRepository.existsBySolicitudId(solicitudId)) {
            throw new RuntimeException("Esta solicitud ya ha sido calificada.");
        }

        // 3. Extraer automáticamente las entidades amarradas desde la solicitud
        // Asumiendo que tu SolicitudModel tiene métodos para obtener al cliente y la oferta/trabajador
        reseñaData.setSolicitud(solicitud);
        reseñaData.setCliente(solicitud.getCliente()); 
        reseñaData.setTrabajador(solicitud.getOferta().getUsuario()); // El dueño de la oferta es el trabajador

        // 4. Guardar en Postgres
        return reseñaRepository.save(reseñaData);
    }

    public List<ReseñaModel> obtenerReseñasPorTrabajador(Integer trabajadorId) {
        return reseñaRepository.findByTrabajadorIdOrderByFechaCreacionDesc(trabajadorId);
    }
}