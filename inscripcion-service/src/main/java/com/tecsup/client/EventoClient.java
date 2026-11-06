package com.tecsup.client;

import com.tecsup.dto.EventoRespuesta;
import com.tecsup.exception.ResourceNotFoundException;
import com.tecsup.exception.ServiceUnavailableException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

/** Llama por HTTP a evento-service para comprobar que el evento existe. */
@Component
public class EventoClient {

    private final RestClient http;

    public EventoClient(@Value("${servicios.evento.url}") String baseUrl) {
        this.http = RestClient.create(baseUrl);
    }

    public EventoRespuesta obtener(Long idEvento) {
        try {
            return http.get().uri("/api/eventos/{id}", idEvento).retrieve().body(EventoRespuesta.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResourceNotFoundException("Evento no encontrado con id: " + idEvento);
        } catch (RestClientException e) {
            throw new ServiceUnavailableException("evento-service no esta disponible en este momento");
        }
    }
}
