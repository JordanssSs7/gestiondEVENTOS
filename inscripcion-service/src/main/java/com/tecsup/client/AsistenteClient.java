package com.tecsup.client;

import com.tecsup.dto.AsistenteRespuesta;
import com.tecsup.exception.ResourceNotFoundException;
import com.tecsup.exception.ServiceUnavailableException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

/** Llama por HTTP a asistente-service para comprobar que el asistente existe. */
@Component
public class AsistenteClient {

    private final RestClient http;

    public AsistenteClient(@Value("${servicios.asistente.url}") String baseUrl) {
        this.http = RestClient.create(baseUrl);
    }

    public AsistenteRespuesta obtener(Long idAsistente) {
        try {
            return http.get().uri("/api/asistentes/{id}", idAsistente).retrieve().body(AsistenteRespuesta.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResourceNotFoundException("Asistente no encontrado con id: " + idAsistente);
        } catch (RestClientException e) {
            throw new ServiceUnavailableException("asistente-service no esta disponible en este momento");
        }
    }
}
