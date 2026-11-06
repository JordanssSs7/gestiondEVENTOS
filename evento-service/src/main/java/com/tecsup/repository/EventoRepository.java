package com.tecsup.repository;

import com.tecsup.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    // Busqueda por nombre (sin distinguir mayusculas)
    List<Evento> findByNombreContainingIgnoreCase(String nombre);

    List<Evento> findByEstado(String estado);
}
