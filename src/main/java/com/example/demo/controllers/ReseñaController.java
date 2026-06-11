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
@CrossOrigin(origins = "https://domu-services.vercel.app") // Tu Front en Vercel
public class ReseñaController {

    private final ReseñaService reseñaService;

    public ReseñaController(ReseñaService reseñaService) {
        this.reseñaService = reseñaService;
    }

    // 📤 POST: Crear una nueva reseña amarrada a una solicitud
    // URL ejemplo: POST https://backenddomu.../api/reseñas/solicitud/25
    @PostMapping("/solicitud/{solicitudId}")
    public ResponseEntity<?> dejasResena(
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

    // 📥 GET: Obtener el historial de reseñas de un trabajador para su perfil
    // URL ejemplo: GET https://backenddomu.../api/reseñas/trabajador/8
    @GetMapping("/trabajador/{trabajadorId}")
    public ResponseEntity<List<ReseñaModel>> obtenerPerfilReseñas(@PathVariable Integer trabajadorId) {
        List<ReseñaModel> reseñas = reseñaService.obtenerReseñasPorTrabajador(trabajadorId);
        return ResponseEntity.ok(reseñas);
    }
}