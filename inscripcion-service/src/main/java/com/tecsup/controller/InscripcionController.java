package com.tecsup.controller;

import com.tecsup.dto.EstadoDTO;
import com.tecsup.dto.InscripcionDTO;
import com.tecsup.model.Inscripcion;
import com.tecsup.services.InscripcionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {

    @Autowired
    private InscripcionService service;

    // GET /api/inscripciones?idEvento=1   |   ?idAsistente=2
    @GetMapping
    public ResponseEntity<List<Inscripcion>> listar(@RequestParam(required = false) Long idEvento,
                                                    @RequestParam(required = false) Long idAsistente) {
        return ResponseEntity.ok(service.listar(idEvento, idAsistente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inscripcion> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PostMapping
    public ResponseEntity<Inscripcion> guardar(@Valid @RequestBody InscripcionDTO dto) {
        return ResponseEntity.status(201).body(service.guardar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inscripcion> actualizar(@PathVariable Long id,
                                                  @Valid @RequestBody InscripcionDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    // PATCH /api/inscripciones/{id}/estado   {"estado":"CANCELADA"}
    @PatchMapping("/{id}/estado")
    public ResponseEntity<Inscripcion> cambiarEstado(@PathVariable Long id,
                                                     @Valid @RequestBody EstadoDTO dto) {
        return ResponseEntity.ok(service.cambiarEstado(id, dto.getEstado()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok(Map.of("mensaje", "Inscripcion eliminada correctamente"));
    }
}
