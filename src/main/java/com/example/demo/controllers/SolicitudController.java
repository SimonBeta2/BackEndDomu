package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.SolicitudModel;
import com.example.demo.services.SolicitudService;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudController {

    @Autowired
    private SolicitudService solicitudService;

    // POST: Cuando un cliente da click a "Solicitar Servicio"
    @PostMapping
    public ResponseEntity<SolicitudModel> crearSolicitud(@RequestBody SolicitudModel solicitud) {
        return ResponseEntity.ok(solicitudService.registrarSolicitud(solicitud));
    }

    // GET: Listar solicitudes enviadas por un cliente específico (Pestaña Solicitudes del Front)
    @GetMapping("/cliente/{clienteId}")
    public List<SolicitudModel> listarPorCliente(@PathVariable Long clienteId) {
        return solicitudService.obtenerPorCliente(clienteId);
    }

    // GET: Listar solicitudes recibidas por un trabajador específico (Pestaña Recibidas del Front)
    @GetMapping("/trabajador/{trabajadorId}")
    public List<SolicitudModel> listarPorTrabajador(@PathVariable Long trabajadorId) {
        return solicitudService.obtenerPorTrabajador(trabajadorId);
    }

}
