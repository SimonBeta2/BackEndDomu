package com.example.demo.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.ServiciosModel;
import com.example.demo.services.ServiciosService;

@RestController
@RequestMapping("/api/servicios")
public class ServiciosController {

    @Autowired
    private ServiciosService serviciosService;

    // POST: Crear servicio
    @PostMapping
    public ResponseEntity<ServiciosModel> crear(@RequestBody ServiciosModel servicios) {
        ServiciosModel nuevoServicio = serviciosService.crearServicio(servicios);
        return new ResponseEntity<>(nuevoServicio, HttpStatus.CREATED);
    }

    // GET: Listar todos
    @GetMapping
    public ResponseEntity<List<ServiciosModel>> listar() {
        return ResponseEntity.ok(serviciosService.listarTodos());
    }

    // GET: Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<ServiciosModel> buscarPorId(@PathVariable Integer id) {
        return serviciosService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT: Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<ServiciosModel> actualizar(@PathVariable Integer id, @RequestBody ServiciosModel servicios) {
        try {
            return ResponseEntity.ok(serviciosService.actualizarServicio(id, servicios));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE: Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            serviciosService.eliminarServicio(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
