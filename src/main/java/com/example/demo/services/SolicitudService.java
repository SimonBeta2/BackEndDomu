package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.OfertaModel;
import com.example.demo.models.SolicitudModel;
import com.example.demo.models.SolicitudUsuarioModel;
import com.example.demo.models.UsuarioModel;
import com.example.demo.repositories.OfertaRepository;
import com.example.demo.repositories.SolicitudRepository;
import com.example.demo.repositories.SolicitudUsuarioRepository;
import com.example.demo.repositories.UsuarioRepository;

@Service
public class SolicitudService {

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    private UsuarioRepository usuarioRepository; 

    @Autowired
    private OfertaRepository ofertaRepository; // O como se llame tu repositorio de ofertas

    @Autowired
    private SolicitudUsuarioRepository solicitudUsuarioRepository;

    public SolicitudModel registrarSolicitud(SolicitudModel solicitud) {

    // 🚨 1. VALIDACIÓN DE SEGURIDAD: ¿El cliente ya tiene este servicio contratado y ACTIVO?
    if (solicitud.getCliente() != null && solicitud.getOferta() != null) {
        
        // Definimos la lista de IDs de estados que se consideran "Activos" y bloquean un nuevo intento.
        // Ej: Supongamos que 1=PENDIENTE, 2=APROBADO, 3=EN_CURSO 4=FINALIZADO 5=CANCELADO (ajústalos según los guardes en tu BD)
        List<Integer> estadosQueBloquean = List.of(1, 2, 3); 

        boolean yaTieneSolicitudActiva = solicitudRepository.findByClienteIdAndOfertaIdAndEstadoIdIn(
            solicitud.getCliente().getId(),
            solicitud.getOferta().getId(),
            estadosQueBloquean
        ).isPresent();

        if (yaTieneSolicitudActiva) {
            throw new RuntimeException("Ya tienes una solicitud activa para este servicio. Debes esperar a que finalice o sea cancelada.");
        }
    }


    // Buscamos al cliente y la oferta como ya lo hacías con tus repositorios estables
    UsuarioModel clienteReal = usuarioRepository.findById(solicitud.getCliente().getId())
        .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        
    OfertaModel ofertaReal = ofertaRepository.findById(solicitud.getOferta().getId())
        .orElseThrow(() -> new RuntimeException("Oferta no encontrada"));

    solicitud.setCliente(clienteReal);
    solicitud.setOferta(ofertaReal);
    
    // El 'direccionId' ya se mapeará solo porque viene directo del JSON plano

    SolicitudModel solicitudGuardada = solicitudRepository.save(solicitud);

    // 🚀 2.5. ¡TRUCO AUTOMÁTICO!: Llenamos la tabla intermedia usando el cliente y la solicitud guardada
    try {
        SolicitudUsuarioModel relacion = new SolicitudUsuarioModel(solicitudGuardada, clienteReal);
        solicitudUsuarioRepository.save(relacion);
        System.out.println("🔗 Fila intermedia registrada en solicitud_usuario para el Cliente ID: " + clienteReal.getId());
    } catch (Exception e) {
        // El try-catch protege tu flujo principal. Si pasa algo con la tabla de adorno, 
        // la solicitud se guarda igual y React no nota ningún error.
        System.out.println("⚠️ Registro en solicitud_usuario omitido de forma segura: " + e.getMessage());
    }

    // 3. Retornamos la solicitud guardada
    return solicitudGuardada;
}

    public List<SolicitudModel> obtenerPorCliente(Integer clienteId) {
        return solicitudRepository.findByClienteId(clienteId);
    }

    public List<SolicitudModel> obtenerPorTrabajador(Integer trabajadorId) {
        return solicitudRepository.findByOfertaUsuarioId(trabajadorId);
    }

}