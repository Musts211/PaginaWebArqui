package com.miempresa.sistema.controller;

import com.miempresa.sistema.model.Viaje;
import com.miempresa.sistema.repository.ViajeRepository;
import com.miempresa.sistema.service.ViajeService;
import com.miempresa.sistema.repository.AutobusRepository;
import com.miempresa.sistema.repository.ChoferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/viajes")
public class VistaViajeController {

        @Autowired
        private ViajeService viajeService;
        @Autowired
        private AutobusRepository autobusRepository;
        @Autowired
        private ChoferRepository choferRepository;
        @Autowired
        private ViajeRepository viajeRepository;


        @GetMapping("/lista")
        public String mostrarViajes(@RequestParam Long usuarioId, Model model) {
            List<Viaje> viajes = viajeService.listarTodosConDisponibilidad();
            model.addAttribute("viajes", viajes);
            model.addAttribute("usuarioId", usuarioId);  // Para que funcione en el onclick del botón
            return "lista_viajes_avanzada";  // Nombre del HTML (sin .html)
        }

    @GetMapping("/registro")
    public String mostrarFormularioRegistro() {
        return "registrarViaje";
    }

    @GetMapping("/testGuardar")
    @ResponseBody
    public String testGuardarViaje() {
        try {
            viajeService.guardarViajeSimple();
            return "Viaje simple guardado con éxito";
        } catch (Exception e) {
            return "Error al guardar viaje simple: " + e.getMessage();
        }
    }
}

