package com.example.demo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.ReseñaModel;
import com.example.demo.services.ReseñaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/reseñas")
@CrossOrigin(origins = "https://domu-services.vercel.app")
public class ReseñaController {

    private final ReseñaService reseñaService;

    public ReseñaController(ReseñaService reseñaService) {
        this.reseñaService = reseñaService;
    }

    @PostMapping("/solicitud/{solicitudId}")
    public ResponseEntity<?> guardarReseña(
            @PathVariable Integer solicitudId, 
            @Valid @RequestBody ReseñaModel reseña
    ) {
        try {
            ReseñaModel nuevaReseña = reseñaService.crearReseña(solicitudId, reseña);
            return ResponseEntity.ok(nuevaReseña);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/trabajador/{trabajadorId}")
    public ResponseEntity<List<ReseñaModel>> obtenerReseñasTrabajador(@PathVariable Integer trabajadorId) {
        List<ReseñaModel> reseñas = reseñaService.obtenerReseñasPorTrabajador(trabajadorId);
        return ResponseEntity.ok(reseñas);
    }
}