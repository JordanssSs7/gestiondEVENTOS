package com.tecsup.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/** Lo que devuelve asistente-service (solo los campos que interesan aqui). */
@JsonIgnoreProperties(ignoreUnknown = true)
public record AsistenteRespuesta(Long id, String nombres, String apellidos, String estado) {
}
