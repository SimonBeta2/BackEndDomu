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
    private final SolicitudRepository solicitudRepository;

    public ReseñaService(ReseñaRepository reseñaRepository, SolicitudRepository solicitudRepository) {
        this.reseñaRepository = reseñaRepository;
        this.solicitudRepository = solicitudRepository;
    }

    @Transactional
    public ReseñaModel crearReseña(Integer solicitudId, ReseñaModel reseñaData) {
        // 1. Validar que la solicitud exista
        SolicitudModel solicitud = solicitudRepository.findById(solicitudId)
            .orElseThrow(() -> new RuntimeException("La solicitud no existe."));

        // 2. Validar que no haya sido calificada antes
        if (reseñaRepository.existsBySolicitudId(solicitudId)) {
            throw new RuntimeException("Esta solicitud ya cuenta con una reseña.");
        }

        // 3. Mapear de forma cruzada usando las propiedades exactas de tus modelos
        reseñaData.setSolicitud(solicitud);
        reseñaData.setCliente(solicitud.getCliente()); // getCliente() de tu SolicitudModel
        reseñaData.setTrabajador(solicitud.getOferta().getUsuario()); // getUsuario() de tu OfertaModel

        return reseñaRepository.save(reseñaData);
    }

    public List<ReseñaModel> obtenerReseñasPorTrabajador(Integer trabajadorId) {
        return reseñaRepository.findByTrabajadorIdOrderByFechaCreacionDesc(trabajadorId);
    }
}