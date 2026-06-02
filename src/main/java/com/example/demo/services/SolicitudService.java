package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.DireccionModel;
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
        // 1. Buscamos al usuario completo en la Base de Datos
        UsuarioModel clienteReal = usuarioRepository.findById(solicitud.getCliente().getId())
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        
        // 2. Extraemos la dirección real buscando dentro de la lista que ya tiene el usuario
        Integer direccionIdBuscado = solicitud.getDireccion().getId();
        DireccionModel direccionReal = clienteReal.getDirecciones().stream()
            .filter(d -> d.getId().equals(direccionIdBuscado))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("La dirección no pertenece a este cliente o no existe"));

        // 3. Buscamos la oferta real para que Hibernate no proteste
        OfertaModel ofertaReal = ofertaRepository.findById(solicitud.getOferta().getId())
            .orElseThrow(() -> new RuntimeException("Oferta no encontrada"));

        // 4. Vinculamos las entidades persistidas a la solicitud
        solicitud.setCliente(clienteReal);
        solicitud.setDireccion(direccionReal);
        solicitud.setOferta(ofertaReal);

        // 5. Guardamos en Railway de forma segura
        return solicitudRepository.save(solicitud);
    }

    public List<SolicitudModel> obtenerPorCliente(Long clienteId) {
        return solicitudRepository.findByClienteId(clienteId);
    }

    public List<SolicitudModel> obtenerPorTrabajador(Long trabajadorId) {
        return solicitudRepository.findByOfertaUsuarioId(trabajadorId);
    }

}