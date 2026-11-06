package com.tecsup.controller;

import com.tecsup.dto.EventoDTO;
import com.tecsup.model.Evento;
import com.tecsup.services.EventoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    @Autowired
    private EventoService service;

    // GET /api/eventos?nombre=...&estado=...
    @GetMapping
    public ResponseEntity<List<Evento>> listar(@RequestParam(required = false) String nombre,
                                               @RequestParam(required = false) String estado) {
        return ResponseEntity.ok(service.listar(nombre, estado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PostMapping
    public ResponseEntity<Evento> guardar(@Valid @RequestBody EventoDTO dto) {
        return ResponseEntity.status(201).body(service.guardar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> actualizar(@PathVariable Long id, @Valid @RequestBody EventoDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok(Map.of("mensaje", "Evento eliminado correctamente"));
    }
}
