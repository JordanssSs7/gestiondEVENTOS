package com.tecsup.controller;

import com.tecsup.dto.AsistenteDTO;
import com.tecsup.model.Asistente;
import com.tecsup.services.AsistenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/asistentes")
public class AsistenteController {

    @Autowired
    private AsistenteService service;

    @GetMapping
    public ResponseEntity<List<Asistente>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asistente> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PostMapping
    public ResponseEntity<Asistente> guardar(@Valid @RequestBody AsistenteDTO dto) {
        return ResponseEntity.status(201).body(service.guardar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asistente> actualizar(@PathVariable Long id,
                                                @Valid @RequestBody AsistenteDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok(Map.of("mensaje", "Asistente eliminado correctamente"));
    }
}
