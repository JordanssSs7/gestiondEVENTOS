package com.tecsup.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class InscripcionDTO {

    @NotNull(message = "El idEvento es obligatorio")
    @Positive(message = "El idEvento debe ser mayor a 0")
    private Long idEvento;

    @NotNull(message = "El idAsistente es obligatorio")
    @Positive(message = "El idAsistente debe ser mayor a 0")
    private Long idAsistente;

    @NotNull(message = "La fecha es obligatoria (formato yyyy-MM-dd)")
    private LocalDate fecha;

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "PENDIENTE|CONFIRMADA|CANCELADA",
            message = "El estado debe ser PENDIENTE, CONFIRMADA o CANCELADA")
    private String estado;

    public Long getIdEvento() { return idEvento; }
    public void setIdEvento(Long idEvento) { this.idEvento = idEvento; }

    public Long getIdAsistente() { return idAsistente; }
    public void setIdAsistente(Long idAsistente) { this.idAsistente = idAsistente; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
