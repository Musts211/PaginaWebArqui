package com.miempresa.sistema.service;

import com.miempresa.sistema.model.Viaje;
import com.miempresa.sistema.repository.ViajeRepository;
import com.miempresa.sistema.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class ViajeService {

    @Autowired
    private ViajeRepository viajeRepository;
    @Autowired
    private ReservaRepository reservaRepository;

    public List<Viaje> buscarViajes(LocalDate fecha, String origen, String destino) {
        return viajeRepository.buscarConFiltros(fecha, origen, destino);
    }

    public List<Viaje> listarTodosConDisponibilidad() {
        List<Viaje> viajes = viajeRepository.findAll();

        for (Viaje v : viajes) {
            long reservados = reservaRepository.countByViajeId(v.getId());
            int cupos = v.getAutobus().getCapacidad() - (int) reservados;
            v.setCuposDisponibles(cupos);
        }
        return viajes;
    }
    public List<Viaje> listarTodos() {
        return viajeRepository.findAll();
    }


    public void guardarViajeSimple() {
        Viaje v = new Viaje();
        v.setFecha(LocalDate.now());
        v.setHora(LocalTime.now());
        v.setOrigen("Origen test");
        v.setDestino("Destino test");
        viajeRepository.save(v);
    }
}