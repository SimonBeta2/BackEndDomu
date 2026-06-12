package com.example.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.FacturaModel;
import com.example.demo.services.FacturaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/facturas")
@CrossOrigin(origins = "https://domu-services.vercel.app")
public class FacturaController {

    private final FacturaService facturaService;

    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    // 📤 POST: Crear factura para una solicitud finalizada
    // URL: POST https://backenddomu-production.up.railway.app/api/facturas/solicitud/25
    @PostMapping("/solicitud/{solicitudId}")
    public ResponseEntity<?> crearFactura(
            @PathVariable Integer solicitudId,
            @Valid @RequestBody FacturaModel factura
    ) {
        try {
            FacturaModel nuevaFactura = facturaService.generarFactura(solicitudId, factura);
            return ResponseEntity.ok(nuevaFactura);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
