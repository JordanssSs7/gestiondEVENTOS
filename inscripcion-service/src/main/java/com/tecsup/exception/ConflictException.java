package com.tecsup.exception;

/** Regla de negocio violada (duplicados, cupos llenos, estados no permitidos...). Responde HTTP 409. */
public class ConflictException extends RuntimeException {
    public ConflictException(String mensaje) {
        super(mensaje);
    }
}
