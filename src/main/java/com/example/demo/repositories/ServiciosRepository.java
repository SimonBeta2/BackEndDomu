package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.ServiciosModel;

@Repository
public interface ServiciosRepository extends JpaRepository<ServiciosModel, Integer> {
    
    // Por ahora no necesitas escribir ningún método aquí adentro.
    // JpaRepository ya te regala el findById(), findAll(), save(), etc.
    
}
