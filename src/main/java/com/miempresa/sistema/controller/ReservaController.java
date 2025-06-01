package com.miempresa.sistema.controller;

import com.miempresa.sistema.model.Reserva;
import com.miempresa.sistema.service.ReservaService;
import com.miempresa.sistema.dto.ReservaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestParam Long usuarioId, @RequestParam Long viajeId) {
        try {
            reservaService.crearReserva(usuarioId, viajeId);
            return ResponseEntity.ok("Reserva creada correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error interno: " + e.getMessage());
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Reserva>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(reservaService.listarPorUsuario(usuarioId));
    }
}