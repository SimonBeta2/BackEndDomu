package com.example.demo.models;


import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "facturas")
public class FacturaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "El monto es obligatorio")
    @Min(value = 1, message = "El monto debe ser mayor a 0")
    @Column(nullable = false)
    private Double monto;

    @NotBlank(message = "La descripción del servicio es obligatoria")
    @Column(columnDefinition = "TEXT", nullable = false)
    private String descripcion;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "fecha_emision", updatable = false, nullable = false)
    private LocalDateTime fechaEmision;

    // Relación 1 a 1: Una solicitud finalizada tiene una única factura
    @OneToOne
    @JoinColumn(name = "solicitud_id", nullable = false, unique = true)
    private SolicitudModel solicitud;

    public FacturaModel() {
    }

    // --- GETTERS Y SETTERS EXPLICITOS PARA MAVEN ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Double getMonto() { return monto; }
    public void setMonto(Double monto) { this.monto = monto; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDateTime getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(LocalDateTime fechaEmision) { this.fechaEmision = fechaEmision; }

    public SolicitudModel getSolicitud() { return solicitud; }
    public void setSolicitud(SolicitudModel solicitud) { this.solicitud = solicitud; }
}
