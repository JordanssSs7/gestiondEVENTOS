package com.tecsup.services;

import com.tecsup.client.AsistenteClient;
import com.tecsup.client.EventoClient;
import com.tecsup.dto.AsistenteRespuesta;
import com.tecsup.dto.EventoRespuesta;
import com.tecsup.dto.InscripcionDTO;
import com.tecsup.exception.BadRequestException;
import com.tecsup.exception.ConflictException;
import com.tecsup.exception.ResourceNotFoundException;
import com.tecsup.model.Inscripcion;
import com.tecsup.repository.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscripcionService {

    private static final String CANCELADA = "CANCELADA";

    @Autowired
    private InscripcionRepository repo;

    @Autowired
    private EventoClient eventoClient;

    @Autowired
    private AsistenteClient asistenteClient;

    public List<Inscripcion> listar(Long idEvento, Long idAsistente) {
        if (idEvento != null) {
            return repo.findByIdEvento(idEvento);
        }
        if (idAsistente != null) {
            return repo.findByIdAsistente(idAsistente);
        }
        return repo.findAll();
    }

    public Inscripcion obtener(Long id) {
        return repo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Inscripcion no encontrada con id: " + id));
    }

    public Inscripcion guardar(InscripcionDTO dto) {
        // 1. El evento y el asistente deben existir (consulta HTTP a los otros microservicios)
        EventoRespuesta evento = eventoClient.obtener(dto.getIdEvento());
        AsistenteRespuesta asistente = asistenteClient.obtener(dto.getIdAsistente());

        // 2. Reglas de negocio
        if (!"ACTIVO".equals(evento.estado())) {
            throw new ConflictException("El evento " + evento.nombre() + " no admite inscripciones (estado "
                    + evento.estado() + ")");
        }
        if (!"ACTIVO".equals(asistente.estado())) {
            throw new ConflictException("El asistente con id " + asistente.id() + " esta inactivo");
        }
        if (!CANCELADA.equals(dto.getEstado())) {
            if (repo.existsByIdEventoAndIdAsistenteAndEstadoNot(dto.getIdEvento(), dto.getIdAsistente(), CANCELADA)) {
                throw new ConflictException("El asistente ya esta inscrito en este evento");
            }
            long ocupados = repo.countByIdEventoAndEstadoNot(dto.getIdEvento(), CANCELADA);
            if (ocupados >= evento.capacidad()) {
                throw new ConflictException("El evento no tiene cupos disponibles (capacidad "
                        + evento.capacidad() + ")");
            }
        }

        // 3. Guardar
        Inscripcion i = new Inscripcion();
        i.setIdEvento(dto.getIdEvento());
        i.setIdAsistente(dto.getIdAsistente());
        i.setFecha(dto.getFecha());
        i.setEstado(dto.getEstado());
        return repo.save(i);
    }

    /** PUT: solo se modifican fecha y estado; el evento y el asistente no cambian. */
    public Inscripcion actualizar(Long id, InscripcionDTO dto) {
        Inscripcion existente = obtener(id);
        if (!existente.getIdEvento().equals(dto.getIdEvento())
                || !existente.getIdAsistente().equals(dto.getIdAsistente())) {
            throw new BadRequestException("No se puede cambiar el evento ni el asistente de una inscripcion; "
                    + "cancele esta y registre una nueva");
        }
        validarCambioEstado(existente, dto.getEstado());
        existente.setFecha(dto.getFecha());
        existente.setEstado(dto.getEstado());
        return repo.save(existente);
    }

    public Inscripcion cambiarEstado(Long id, String nuevoEstado) {
        Inscripcion existente = obtener(id);
        validarCambioEstado(existente, nuevoEstado);
        existente.setEstado(nuevoEstado);
        return repo.save(existente);
    }

    public void eliminar(Long id) {
        repo.delete(obtener(id));
    }

    private void validarCambioEstado(Inscripcion actual, String nuevoEstado) {
        if (CANCELADA.equals(actual.getEstado()) && !CANCELADA.equals(nuevoEstado)) {
            throw new ConflictException("Una inscripcion CANCELADA no se puede reactivar; registre una nueva");
        }
    }
}
