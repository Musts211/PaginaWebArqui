package com.miempresa.sistema.controller;
import com.miempresa.sistema.model.Autobus;
import com.miempresa.sistema.repository.AutobusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/autobuses")
public class AutobusController {
    @Autowired
    private AutobusRepository autobusRepository;

    @GetMapping
    public List<Autobus> listarTodos() {
        return autobusRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Autobus autobus) {
        // Validar formato de placa (AA-1234)
        if (!autobus.getPlaca().matches("^[A-Z]{2}-\\d{4}$")) {
            return ResponseEntity.badRequest().body("Formato de placa inválido. Debe ser AA-1234.");
        }

        Autobus guardado = autobusRepository.save(autobus);
        return ResponseEntity.ok(guardado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autobus> obtenerPorId(@PathVariable Long id) {
        return autobusRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
