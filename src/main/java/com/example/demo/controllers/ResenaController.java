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

import com.example.demo.models.ResenaModel;
import com.example.demo.services.ResenaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/resenas")
@CrossOrigin(origins = "https://domu-services.vercel.app")
public class ResenaController {

    private final ResenaService resenaService;

    public ResenaController(ResenaService resenaService) {
        this.resenaService = resenaService;
    }

    @PostMapping("/solicitud/{solicitudId}")
    public ResponseEntity<?> guardarReseña(
            @PathVariable Integer solicitudId, 
            @Valid @RequestBody ResenaModel reseña
    ) {
        try {
            ResenaModel nuevaReseña = resenaService.crearReseña(solicitudId, reseña);
            return ResponseEntity.ok(nuevaReseña);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/trabajador/{trabajadorId}")
    public ResponseEntity<List<ResenaModel>> obtenerReseñasTrabajador(@PathVariable Integer trabajadorId) {
        List<ResenaModel> reseñas = resenaService.obtenerReseñasPorTrabajador(trabajadorId);
        return ResponseEntity.ok(reseñas);
    }
}