package com.example.demo.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.ServiciosModel;
import com.example.demo.repositories.ServiciosRepository;

@Service
public class ServiciosService {

    @Autowired
    private ServiciosRepository serviciosRepository;

    // Crear o guardar un servicio
    public ServiciosModel crearServicio(ServiciosModel servicio) {
        return serviciosRepository.save(servicio);
    }

    // Listar todos los servicios
    public List<ServiciosModel> listarTodos() {
        return serviciosRepository.findAll();
    }

    // Buscar servicio por ID
    public Optional<ServiciosModel> buscarPorId(Integer id) {
        return serviciosRepository.findById(id);
    }

    // Actualizar un servicio
    public ServiciosModel actualizarServicio(Integer id, ServiciosModel detallesServicio) {
        return serviciosRepository.findById(id).map(servicioExistente -> {
            servicioExistente.setNombre(detallesServicio.getNombre());
            // Agrega aquí otros campos si tu entidad Servicios tiene más atributos
            return serviciosRepository.save(servicioExistente);
        }).orElseThrow(() -> new RuntimeException("Servicio no encontrado con el ID: " + id));
    }

    // Eliminar un servicio
    public void eliminarServicio(Integer id) {
        if(serviciosRepository.existsById(id)) {
            serviciosRepository.deleteById(id);
        } else {
            throw new RuntimeException("No se puede eliminar. Servicio no encontrado con ID: " + id);
        }
    }
}
