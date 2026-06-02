package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.OfertaTrabajadorModel;

@Repository
public interface OfertaTrabajadorRepository extends JpaRepository<OfertaTrabajadorModel, Integer> {
    // No necesitamos métodos complejos, con el .save() básico nos basta
}