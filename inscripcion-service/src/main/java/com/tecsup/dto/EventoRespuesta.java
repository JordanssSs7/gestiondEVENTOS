package com.tecsup.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

/** Lo que devuelve evento-service (solo los campos que interesan aqui). */
@JsonIgnoreProperties(ignoreUnknown = true)
public record EventoRespuesta(Long id, String nombre, LocalDate fecha, Integer capacidad, String estado) {
}
