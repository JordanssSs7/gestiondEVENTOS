package com.tecsup.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class EventoDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El lugar es obligatorio")
    private String lugar;

    @NotNull(message = "La fecha es obligatoria (formato yyyy-MM-dd)")
    private LocalDate fecha;

    @NotNull(message = "La capacidad es obligatoria")
    @Positive(message = "La capacidad debe ser mayor a 0")
    private Integer capacidad;

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "ACTIVO|CANCELADO|FINALIZADO",
            message = "El estado debe ser ACTIVO, CANCELADO o FINALIZADO")
    private String estado;

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getLugar() { return lugar; }
    public void setLugar(String lugar) { this.lugar = lugar; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public Integer getCapacidad() { return capacidad; }
    public void setCapacidad(Integer capacidad) { this.capacidad = capacidad; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
