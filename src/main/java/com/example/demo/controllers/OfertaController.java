package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.OfertaModel;
import com.example.demo.services.OfertaService;

@RestController
@RequestMapping("/api/ofertas")
public class OfertaController {

    @Autowired
    private OfertaService ofertaService;

    // PUBLICO: Ver todas las ofertas
    @GetMapping
    public List<OfertaModel> listar() {
        return ofertaService.listarTodas();
    }

    // PUBLICO: Ver ofertas por categoría (Tu método mágico)
    @GetMapping("/servicio/{servicioId}")
    public List<OfertaModel> buscarPorServicio(@PathVariable Integer servicioId) {
        return ofertaService.buscarPorServicio(servicioId);
    }

    // PRIVADO (Requiere Token): Crear una oferta
    @PostMapping
    public OfertaModel crear(@RequestBody OfertaModel oferta) {
        return ofertaService.guardarOferta(oferta);
    }
}