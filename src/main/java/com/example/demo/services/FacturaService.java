package com.example.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.FacturaModel;
import com.example.demo.models.SolicitudModel;
import com.example.demo.repositories.FacturaRepository;
import com.example.demo.repositories.SolicitudRepository;

@Service
public class FacturaService {

    private final FacturaRepository facturaRepository;
    private final SolicitudRepository solicitudRepository;

    public FacturaService(FacturaRepository facturaRepository, SolicitudRepository solicitudRepository) {
        this.facturaRepository = facturaRepository;
        this.solicitudRepository = solicitudRepository;
    }

    @Transactional
    public FacturaModel generarFactura(Integer solicitudId, FacturaModel facturaData) {
        // 1. Buscar la solicitud
        SolicitudModel solicitud = solicitudRepository.findById(solicitudId)
            .orElseThrow(() -> new RuntimeException("La solicitud especificada no existe."));

        // 2. REGLA: Validar que el estado de la solicitud sea estrictamente "FINALIZADO"
        if (solicitud.getEstado() == null || !solicitud.getEstado().getNombre().equalsIgnoreCase("FINALIZADO")) {
            throw new RuntimeException("No se puede facturar una solicitud que no esté en estado FINALIZADO.");
        }

        // 3. REGLA: Validar que no se haya facturado antes
        if (facturaRepository.existsBySolicitudId(solicitudId)) {
            throw new RuntimeException("Esta solicitud ya cuenta con una factura emitida.");
        }

        // 4. Vincular y guardar
        facturaData.setSolicitud(solicitud);
        return facturaRepository.save(facturaData);
    }
}