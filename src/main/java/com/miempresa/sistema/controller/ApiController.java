package com.miempresa.sistema.controller;
import com.miempresa.sistema.model.Autobus;
import com.miempresa.sistema.model.Chofer;
import com.miempresa.sistema.model.Viaje;
import com.miempresa.sistema.dto.ViajeDTO;
import com.miempresa.sistema.repository.AutobusRepository;
import com.miempresa.sistema.repository.ChoferRepository;
import com.miempresa.sistema.repository.ViajeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private AutobusRepository autobusRepository;

    @Autowired
    private ViajeRepository viajeRepository;

    @Autowired
    private ChoferRepository choferRepository;

    @GetMapping("/todos-autobuses")
    public List<Autobus> listarAutobuses() {
        return autobusRepository.findAll();
    }

    @GetMapping("/todos-choferes")
    public List<Chofer> listarChoferes() {
        return choferRepository.findAll();
    }

    @PostMapping("/viajes")
    public ResponseEntity<?> registrarViaje(@RequestBody ViajeDTO viajeDTO) {
        try {
            Viaje viaje = new Viaje();
            viaje.setFecha(LocalDate.parse(viajeDTO.getFecha()));

            String horaStr = viajeDTO.getHora();
            if (horaStr.length() == 5) { // Si es formato "HH:mm"
                horaStr += ":00";         // Lo convertimos a "HH:mm:ss"
            }
            viaje.setHora(LocalTime.parse(horaStr));

            viaje.setOrigen(viajeDTO.getOrigen());
            viaje.setDestino(viajeDTO.getDestino());

            Autobus autobus = autobusRepository.findById(viajeDTO.getAutobusId())
                    .orElseThrow(() -> new RuntimeException("Autobús no encontrado"));

            Chofer chofer = choferRepository.findById(viajeDTO.getChoferId())
                    .orElseThrow(() -> new RuntimeException("Chofer no encontrado"));
            viaje.setAutobus(autobus);
            viaje.setChofer(chofer);

            // Guardar
            viajeRepository.save(viaje);

            return ResponseEntity.ok("Viaje registrado con éxito");

        } catch (Exception e) {
            e.printStackTrace();  // Esto muestra el error completo en consola
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }
}
