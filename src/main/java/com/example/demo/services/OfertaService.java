package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.OfertaModel;
import com.example.demo.models.ServiciosModel;
import com.example.demo.models.UsuarioModel;
import com.example.demo.repositories.OfertaRepository;
import com.example.demo.repositories.ServiciosRepository;
import com.example.demo.repositories.UsuarioRepository;

@Service
public class OfertaService {

    @Autowired
    private OfertaRepository ofertaRepository;

    // Inyectamos los otros repositorios para poder buscar los datos
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ServiciosRepository serviciosRepository;

    public OfertaModel guardarOferta(OfertaModel oferta) {
        // 1. Buscamos el usuario real en la base de datos usando el ID que llegó
        UsuarioModel usuarioReal = usuarioRepository.findById(oferta.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 2. Buscamos el servicio real
        ServiciosModel servicioReal = serviciosRepository.findById(oferta.getServicio().getId())
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        // 3. Reemplazamos los objetos "vacíos" que llegaron del JSON por los objetos reales y completos
        oferta.setUsuario(usuarioReal);
        oferta.setServicio(servicioReal);

        // 4. Guardamos y retornamos. ¡Ahora el JSON de salida estará completo!
        return ofertaRepository.save(oferta);
    }

    public List<OfertaModel> listarTodas() {
        return ofertaRepository.findAll();
    }

    public List<OfertaModel> buscarPorServicio(Integer servicioId) {
        return ofertaRepository.findByServicioId(servicioId);
    }
}