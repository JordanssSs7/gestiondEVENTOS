package com.tecsup.exception;

/** Otro microservicio no responde. Responde HTTP 503. */
public class ServiceUnavailableException extends RuntimeException {
    public ServiceUnavailableException(String mensaje) {
        super(mensaje);
    }
}
