package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ofertas")
public class OfertaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String experiencia;
    
    @Column(length = 500)
    private String descripcion;
    
    private String rangoPrecio;
    private String disponibilidad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioModel usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "servicio_id", nullable = false)
    private ServiciosModel servicio;

    public OfertaModel() {
    }

    public OfertaModel(String experiencia, String descripcion, String rangoPrecio, String disponibilidad) {
        this.experiencia = experiencia;
        this.descripcion = descripcion;
        this.rangoPrecio = rangoPrecio;
        this.disponibilidad = disponibilidad;
    }

    // 🚨 LOS GETTERS Y SETTERS QUE LE FALTABAN A MAVEN:
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public UsuarioModel getUsuario() { return usuario; }
    public void setUsuario(UsuarioModel usuario) { this.usuario = usuario; }

    public ServiciosModel getServicio() { return servicio; }
    public void setServicio(ServiciosModel servicio) { this.servicio = servicio; }

    // Getters y setters que ya tenías:
    public String getExperiencia() { return experiencia; }
    public void setExperiencia(String experiencia) { this.experiencia = experiencia; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getRangoPrecio() { return rangoPrecio; }
    public void setRangoPrecio(String rangoPrecio) { this.rangoPrecio = rangoPrecio; }

    public String getDisponibilidad() { return disponibilidad; }
    public void setDisponibilidad(String disponibilidad) { this.disponibilidad = disponibilidad; }
}