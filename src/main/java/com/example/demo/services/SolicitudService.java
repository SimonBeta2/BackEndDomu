package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.OfertaModel;
import com.example.demo.models.SolicitudModel;
import com.example.demo.models.UsuarioModel;
import com.example.demo.repositories.OfertaRepository;
import com.example.demo.repositories.SolicitudRepository;
import com.example.demo.repositories.UsuarioRepository;

@Service
public class SolicitudService {

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    private UsuarioRepository usuarioRepository; 

    @Autowired
    private OfertaRepository ofertaRepository; // O como se llame tu repositorio de ofertas

    public SolicitudModel registrarSolicitud(SolicitudModel solicitud) {
    // Buscamos al cliente y la oferta como ya lo hacías con tus repositorios estables
    UsuarioModel clienteReal = usuarioRepository.findById(solicitud.getCliente().getId())
        .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        
    OfertaModel ofertaReal = ofertaRepository.findById(solicitud.getOferta().getId())
        .orElseThrow(() -> new RuntimeException("Oferta no encontrada"));

    solicitud.setCliente(clienteReal);
    solicitud.setOferta(ofertaReal);
    
    // El 'direccionId' ya se mapeará solo porque viene directo del JSON plano

    return solicitudRepository.save(solicitud);
}

    public List<SolicitudModel> obtenerPorCliente(Long clienteId) {
        return solicitudRepository.findByClienteId(clienteId);
    }

    public List<SolicitudModel> obtenerPorTrabajador(Long trabajadorId) {
        return solicitudRepository.findByOfertaUsuarioId(trabajadorId);
    }

}