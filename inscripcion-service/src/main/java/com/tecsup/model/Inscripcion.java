package com.tecsup.model;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * No hay llaves foraneas hacia las otras bases de datos: solo se guardan los IDs
 * (idEvento -> evento-service, idAsistente -> asistente-service).
 */
@Entity
@Table(name = "inscripcion")
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long idEvento;

    @Column(nullable = false)
    private Long idAsistente;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false, length = 20)
    private String estado;

    public Inscripcion() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getIdEvento() { return idEvento; }
    public void setIdEvento(Long idEvento) { this.idEvento = idEvento; }

    public Long getIdAsistente() { return idAsistente; }
    public void setIdAsistente(Long idAsistente) { this.idAsistente = idAsistente; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
