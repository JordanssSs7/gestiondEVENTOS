package com.tecsup.services;

import com.tecsup.dto.EventoDTO;
import com.tecsup.exception.ResourceNotFoundException;
import com.tecsup.model.Evento;
import com.tecsup.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository repo;

    public List<Evento> listar(String nombre, String estado) {
        if (nombre != null && !nombre.isBlank()) {
            return repo.findByNombreContainingIgnoreCase(nombre);
        }
        if (estado != null && !estado.isBlank()) {
            return repo.findByEstado(estado.toUpperCase());
        }
        return repo.findAll();
    }

    public Evento obtener(Long id) {
        return repo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Evento no encontrado con id: " + id));
    }

    public Evento guardar(EventoDTO dto) {
        Evento e = new Evento();
        copiar(dto, e);
        return repo.save(e);
    }

    public Evento actualizar(Long id, EventoDTO dto) {
        Evento existente = obtener(id);
        copiar(dto, existente);
        return repo.save(existente);
    }

    public void eliminar(Long id) {
        repo.delete(obtener(id));
    }

    private void copiar(EventoDTO dto, Evento e) {
        e.setNombre(dto.getNombre());
        e.setLugar(dto.getLugar());
        e.setFecha(dto.getFecha());
        e.setCapacidad(dto.getCapacidad());
        e.setEstado(dto.getEstado());
    }
}
