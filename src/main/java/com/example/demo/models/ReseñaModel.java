package com.example.demo.models;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
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

    // ⭐️ 1. ATRIBUTO ENTERO (1 a 5) con validaciones de Spring
    @NotNull(message = "La calificación es obligatoria")
    @Min(value = 1, message = "La calificación mínima es 1")
    @Max(value = 5, message = "La calificación máxima es 5")
    @Column(name = "calificacion", nullable = false)
    private Integer calificacion;

    // 💬 2. ATRIBUTO COMENTARIOS
    // Usamos columnDefinition = "TEXT" por si el cliente se extiende escribiendo
    @Column(name = "comentario", columnDefinition = "TEXT")
    private String comentario;

    // 📅 Atributo extra muy útil para ordenar por las más recientes
    @CreationTimestamp
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    // 🔗 3. RELACIONES (Cruciales para tu BD en Railway)
    
    // Una reseña nace a partir de una solicitud de servicio finalizada
    @OneToOne
    @JoinColumn(name = "solicitud_id", nullable = false, unique = true)
    private SolicitudModel solicitud;

    // El cliente que escribe la reseña (opcional si ya puedes sacarlo desde la solicitud)
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private UsuarioModel cliente;

    // El trabajador que recibe la puntuación (útil para sacar su promedio de estrellas rápido)
    @ManyToOne
    @JoinColumn(name = "trabajador_id", nullable = false)
    private UsuarioModel trabajador;

    // --- CONSTRUCTORES ---
    public ReseñaModel() {
    }

    // Bloque que se ejecuta automáticamente antes de insertar en la BD
    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
    }

    // --- GETTERS Y SETTERS ---
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public SolicitudModel getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(SolicitudModel solicitud) {
        this.solicitud = solicitud;
    }

    public UsuarioModel getCliente() {
        return cliente;
    }

    public void setCliente(UsuarioModel cliente) {
        this.cliente = cliente;
    }

    public UsuarioModel getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(UsuarioModel trabajador) {
        this.trabajador = trabajador;
    }
}