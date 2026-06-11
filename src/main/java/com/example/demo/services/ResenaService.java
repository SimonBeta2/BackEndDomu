package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.ResenaModel;
import com.example.demo.models.SolicitudModel;
import com.example.demo.repositories.ResenaRepository;
import com.example.demo.repositories.SolicitudRepository;

@Service
public class ResenaService {

    private final ResenaRepository resenaRepository;
    private final SolicitudRepository solicitudRepository;

    public ResenaService(ResenaRepository resenaRepository, SolicitudRepository solicitudRepository) {
        this.resenaRepository = resenaRepository;
        this.solicitudRepository = solicitudRepository;
    }

    @Transactional
    public ResenaModel crearReseña(Integer solicitudId, ResenaModel reseñaData) {
        // 1. Validar que la solicitud exista
        SolicitudModel solicitud = solicitudRepository.findById(solicitudId)
            .orElseThrow(() -> new RuntimeException("La solicitud no existe."));

        // 2. Validar que no haya sido calificada antes
        if (resenaRepository.existsBySolicitudId(solicitudId)) {
            throw new RuntimeException("Esta solicitud ya cuenta con una reseña.");
        }

        // 3. Mapear de forma cruzada usando las propiedades exactas de tus modelos
        reseñaData.setSolicitud(solicitud);
        reseñaData.setCliente(solicitud.getCliente()); // getCliente() de tu SolicitudModel
        reseñaData.setTrabajador(solicitud.getOferta().getUsuario()); // getUsuario() de tu OfertaModel

        return resenaRepository.save(reseñaData);
    }

    public List<ResenaModel> obtenerReseñasPorTrabajador(Integer trabajadorId) {
        return resenaRepository.findByTrabajadorIdOrderByFechaCreacionDesc(trabajadorId);
    }
}