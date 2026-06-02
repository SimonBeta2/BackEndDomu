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
import jakarta.persistence.Table;

@Entity
@Table(name = "solicitudes")
public class SolicitudModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // El cliente que está contratando/solicitando el servicio
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private UsuarioModel cliente;

    // La oferta específica del trabajador que fue seleccionada
    @ManyToOne
    @JoinColumn(name = "oferta_id", nullable = false)
    private OfertaModel oferta;

    // Detalles adicionales que el cliente escribe (ej: "Se rompió un tubo en el baño")
    @Column(nullable = false, length = 500)
    private String descripcionProblema;
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaSolicitud;

    @Column(name = "direccion_id", nullable = true)
    private String direccion;

    // 2. Fecha preferida (Guarda YYYY-MM-DD)
    @Column(nullable = false)
    private java.time.LocalDate fechaPreferida;

    // 3. Hora preferida (Guarda HH:mm:ss)
    @Column(nullable = false)
    private java.time.LocalTime horaPreferida;

    // --- GETTERS Y SETTERS ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public UsuarioModel getCliente() { return cliente; }
    public void setCliente(UsuarioModel cliente) { this.cliente = cliente; }

    public OfertaModel getOferta() { return oferta; }
    public void setOferta(OfertaModel oferta) { this.oferta = oferta; }

    public String getDescripcionProblema() { return descripcionProblema; }
    public void setDescripcionProblema(String descripcionProblema) { this.descripcionProblema = descripcionProblema; }

    public LocalDateTime getFechaSolicitud() { return fechaSolicitud; }
    public void setFechaSolicitud(LocalDateTime fechaSolicitud) { this.fechaSolicitud = fechaSolicitud; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public java.time.LocalDate getFechaPreferida() { return fechaPreferida; }
    public void setFechaPreferida(java.time.LocalDate fechaPreferida) { this.fechaPreferida = fechaPreferida; }

    public java.time.LocalTime getHoraPreferida() { return horaPreferida; }
    public void setHoraPreferida(java.time.LocalTime horaPreferida) { this.horaPreferida = horaPreferida; }

}