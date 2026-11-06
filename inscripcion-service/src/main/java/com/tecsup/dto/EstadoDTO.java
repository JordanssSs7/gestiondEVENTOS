package com.tecsup.dto;

import jakarta.validation.constraints.*;

/** Cuerpo para PATCH /api/inscripciones/{id}/estado */
public class EstadoDTO {

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "PENDIENTE|CONFIRMADA|CANCELADA",
            message = "El estado debe ser PENDIENTE, CONFIRMADA o CANCELADA")
    private String estado;

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
