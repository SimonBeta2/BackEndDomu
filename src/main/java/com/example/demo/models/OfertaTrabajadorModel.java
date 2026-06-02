package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "oferta_trabajador")
public class OfertaTrabajadorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "trabajador_id", referencedColumnName = "id")
    private TrabajadorModel trabajador;

    @ManyToOne
    @JoinColumn(name = "oferta_id", referencedColumnName = "id")
    private OfertaModel oferta;

    // --- CONSTRUCTORES ---
    public OfertaTrabajadorModel() {}

    public OfertaTrabajadorModel(TrabajadorModel trabajador, OfertaModel oferta) {
        this.trabajador = trabajador;
        this.oferta = oferta;
    }

    // --- GETTERS Y SETTERS ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public TrabajadorModel getTrabajador() { return trabajador; }
    public void setTrabajador(TrabajadorModel trabajador) { this.trabajador = trabajador; }

    public OfertaModel getOferta() { return oferta; }
    public void setOferta(OfertaModel oferta) { this.oferta = oferta; }
}
