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
import lombok.Data;

@Data
@Entity
@Table(name = "ofertas")
public class OfertaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200) // Permite textos largos
    private String experiencia;
    
    @Column(length=500) // Permite textos aún más largos
    private String descripcion;
    
    private String rangoPrecio;
    private String disponibilidad;

    // --- RELACIÓN CON EL USUARIO (El que ofrece) ---
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    // Nota: Aquí NO ponemos @JsonIgnore, porque cuando alguien busque una oferta,
    // SÍ queremos que el JSON muestre los datos del usuario (para poder contactarlo).
    private UsuarioModel usuario;

    // --- RELACIÓN CON EL SERVICIO (La categoría) ---
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "servicio_id", nullable = false)
    private ServiciosModel servicio;
}
