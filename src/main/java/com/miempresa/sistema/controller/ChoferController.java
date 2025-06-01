package com.miempresa.sistema.controller;
import com.miempresa.sistema.model.Chofer;
import com.miempresa.sistema.repository.ChoferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/choferes")

public class ChoferController {
    @Autowired
    private ChoferRepository choferRepository;

    @GetMapping
    public List<Chofer> listarTodos() {
        return choferRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Chofer chofer) {
        // Validar formato de código de chofer (CH-1234)
        if (!chofer.getCodigo().matches("^CH-\\d{4}$")) {
            return ResponseEntity.badRequest().body("Formato de código inválido. Debe ser CH-1234.");
        }

        Chofer guardado = choferRepository.save(chofer);
        return ResponseEntity.ok(guardado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chofer> obtenerPorId(@PathVariable Long id) {
        return choferRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
