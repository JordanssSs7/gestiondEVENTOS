package com.tecsup.exception;

/** Solicitud mal formada a nivel de negocio. Responde HTTP 400. */
public class BadRequestException extends RuntimeException {
    public BadRequestException(String mensaje) {
        super(mensaje);
    }
}
