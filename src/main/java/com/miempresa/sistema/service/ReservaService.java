package com.miempresa.sistema.service;
import com.miempresa.sistema.model.*;
import com.miempresa.sistema.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import com.miempresa.sistema.dto.ReservaDTO;



@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ViajeRepository viajeRepository;

    public ReservaDTO crearReserva(Long usuarioId, Long viajeId) {
        try {
            Usuario usuario = usuarioRepository.findById(usuarioId)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + usuarioId));

            Viaje viaje = viajeRepository.findById(viajeId)
                    .orElseThrow(() -> new RuntimeException("Viaje no encontrado con ID: " + viajeId));

            if (viaje.getAutobus() == null) {
                throw new RuntimeException("El viaje no tiene asignado un autobús.");
            }

            int capacidadBus = viaje.getAutobus().getCapacidad();

            long reservasActuales = reservaRepository.countByViajeId(viajeId);

            if (reservasActuales >= capacidadBus) {
                throw new RuntimeException("No hay asientos disponibles para este viaje.");
            }

            Reserva reserva = new Reserva();
            reserva.setUsuario(usuario);
            reserva.setViaje(viaje);
            reserva.setFechaReserva(LocalDate.now());

            Reserva reservaGuardada = reservaRepository.save(reserva);

            return convertirADTO(reservaGuardada);
        } catch (Exception e) {
            e.printStackTrace(); // ver error en consola
            throw e; // relanzar para que controlador capture
        }
    }


    public List<Reserva> listarPorUsuario(Long usuarioId) {

        return reservaRepository.findByUsuarioId(usuarioId);
    }
    private ReservaDTO convertirADTO(Reserva reserva) {
        return new ReservaDTO(
                reserva.getId(),
                reserva.getFechaReserva(),
                reserva.getUsuario().getId(),
                reserva.getUsuario().getNombre(),
                reserva.getViaje().getId(),
                reserva.getViaje().getOrigen(),
                reserva.getViaje().getDestino()
        );
    }
}