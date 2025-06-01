package com.miempresa.sistema.controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.miempresa.sistema.model.*;
import com.miempresa.sistema.service.*;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;


import java.util.List;

@Controller
public class VistaController {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private ViajeService viajeService;

    @GetMapping("/autobus/registro")
    public String mostrarFormularioAutobus() {
        return "registarAutobus";
    }

    @GetMapping("/chofer/registro")
    public String mostrarFormularioChofer() {
        return "registroChofer";
    }

    @GetMapping("/reservar")
    public String formularioReserva() {
        return "reservar";
    }

    @GetMapping("/mis-reservas")
    public String verReservas(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/login"; // redirige si no hay sesión
        }

        List<Reserva> reservas = reservaService.listarPorUsuario(usuario.getId());
        model.addAttribute("reservas", reservas);
        return "listar_reservas"; // nombre de tu HTML
    }

    @GetMapping("/viajes")
    public String verViajes(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
                            @RequestParam(required = false) String origen,
                            @RequestParam(required = false) String destino,
                            @RequestParam(required = false) Long usuarioId,
                            Model model) {
        System.out.println("Fecha: " + fecha);
        System.out.println("Origen: " + origen);
        System.out.println("Destino: " + destino);
        System.out.println("Usuario ID: " + usuarioId);

        List<Viaje> viajes = viajeService.buscarViajes(fecha, origen, destino);
        model.addAttribute("viajes", viajes);
        model.addAttribute("fecha", fecha);
        model.addAttribute("origen", origen);
        model.addAttribute("destino", destino);
        model.addAttribute("usuarioId", usuarioId);

        return "listar_viajes";
    }
}