package com.tecsup.services;

import com.tecsup.dto.AsistenteDTO;
import com.tecsup.exception.ConflictException;
import com.tecsup.exception.ResourceNotFoundException;
import com.tecsup.model.Asistente;
import com.tecsup.repository.AsistenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsistenteService {

    @Autowired
    private AsistenteRepository repo;

    public List<Asistente> listar() {
        return repo.findAll();
    }

    public Asistente obtener(Long id) {
        return repo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Asistente no encontrado con id: " + id));
    }

    public Asistente guardar(AsistenteDTO dto) {
        if (repo.existsByDocumento(dto.getDocumento())) {
            throw new ConflictException("Ya existe un asistente con el documento: " + dto.getDocumento());
        }
        Asistente a = new Asistente();
        copiar(dto, a);
        return repo.save(a);
    }

    public Asistente actualizar(Long id, AsistenteDTO dto) {
        Asistente existente = obtener(id);
        if (repo.existsByDocumentoAndIdNot(dto.getDocumento(), id)) {
            throw new ConflictException("Ya existe otro asistente con el documento: " + dto.getDocumento());
        }
        copiar(dto, existente);
        return repo.save(existente);
    }

    public void eliminar(Long id) {
        repo.delete(obtener(id));
    }

    private void copiar(AsistenteDTO dto, Asistente a) {
        a.setDocumento(dto.getDocumento());
        a.setNombres(dto.getNombres());
        a.setApellidos(dto.getApellidos());
        a.setCorreo(dto.getCorreo());
        a.setTelefono(dto.getTelefono());
        a.setEstado(dto.getEstado());
    }
}
