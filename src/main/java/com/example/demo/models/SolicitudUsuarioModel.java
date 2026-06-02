package com.example.demo.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "solicitud_usuario")
public class SolicitudUsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "solicitud_id", referencedColumnName = "id")
    private SolicitudModel solicitud; // Cambia 'SolicitudModel' por el nombre exacto de tu clase de solicitudes

    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private UsuarioModel usuario;

    // --- CONSTRUCTORES ---
    public SolicitudUsuarioModel() {}

    public SolicitudUsuarioModel(SolicitudModel solicitud, UsuarioModel usuario) {
        this.solicitud = solicitud;
        this.usuario = usuario;
    }

    // --- GETTERS Y SETTERS ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public SolicitudModel getSolicitud() { return solicitud; }
    public void setSolicitud(SolicitudModel solicitud) { this.solicitud = solicitud; }

    public UsuarioModel getUsuario() { return usuario; }
    public void setUsuario(UsuarioModel usuario) { this.usuario = usuario; }
}
