package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.SolicitudModel;
import com.example.demo.repositories.SolicitudRepository;

@Service
public class SolicitudService {

    @Autowired
    private SolicitudRepository solicitudRepository;

    public SolicitudModel registrarSolicitud(SolicitudModel solicitud) {
        return solicitudRepository.save(solicitud);
    }

    public List<SolicitudModel> obtenerPorCliente(Long clienteId) {
        return solicitudRepository.findByClienteId(clienteId);
    }

    public List<SolicitudModel> obtenerPorTrabajador(Long trabajadorId) {
        return solicitudRepository.findByOfertaUsuarioId(trabajadorId);
    }

}