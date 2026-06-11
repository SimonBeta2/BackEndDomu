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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "reseñas")
public class ReseñaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "La calificación es obligatoria")
    @Min(value = 1, message = "Mínimo 1 estrella")
    @Max(value = 5, message = "Máximo 5 estrellas")
    @Column(nullable = false)
    private Integer calificacion;

    @Column(columnDefinition = "TEXT")
    private String comentario;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "fecha_creacion", updatable = false, nullable = false)
    private LocalDateTime fechaCreacion;

    // Relación 1 a 1 con la solicitud (Cada solicitud se califica una sola vez)
    @OneToOne
    @JoinColumn(name = "solicitud_id", nullable = false, unique = true)
    private SolicitudModel solicitud;

    // Cliente que califica
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private UsuarioModel cliente;

    // Trabajador que recibe la calificación
    @ManyToOne
    @JoinColumn(name = "trabajador_id", nullable = false)
    private UsuarioModel trabajador;

    public ReseñaModel() {
    }

    // --- GETTERS Y SETTERS MANUALES SEGUROS ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getCalificacion() { return calificacion; }
    public void setCalificacion(Integer calificacion) { this.calificacion = calificacion; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public SolicitudModel getSolicitud() { return solicitud; }
    public void setSolicitud(SolicitudModel solicitud) { this.solicitud = solicitud; }

    public UsuarioModel getCliente() { return cliente; }
    public void setCliente(UsuarioModel cliente) { this.cliente = cliente; }

    public UsuarioModel getTrabajador() { return trabajador; }
    public void setTrabajador(UsuarioModel trabajador) { this.trabajador = trabajador; }
}