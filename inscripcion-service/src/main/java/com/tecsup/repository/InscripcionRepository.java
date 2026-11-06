package com.tecsup.repository;

import com.tecsup.model.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {

    List<Inscripcion> findByIdEvento(Long idEvento);

    List<Inscripcion> findByIdAsistente(Long idAsistente);

    // Inscripciones que ocupan un cupo (todas menos las CANCELADAS)
    long countByIdEventoAndEstadoNot(Long idEvento, String estado);

    boolean existsByIdEventoAndIdAsistenteAndEstadoNot(Long idEvento, Long idAsistente, String estado);
}
