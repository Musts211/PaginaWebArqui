package com.miempresa.sistema.dto;
import java.time.LocalDate;

public class ReservaDTO {
    private Long id;
    private LocalDate fechaReserva;
    private Long usuarioId;
    private String nombreUsuario;
    private Long viajeId;
    private String origen;
    private String destino;

    // Constructor
    public ReservaDTO(Long id, LocalDate fechaReserva, Long usuarioId, String nombreUsuario,
                      Long viajeId, String origen, String destino) {
        this.id = id;
        this.fechaReserva = fechaReserva;
        this.usuarioId = usuarioId;
        this.nombreUsuario = nombreUsuario;
        this.viajeId = viajeId;
        this.origen = origen;
        this.destino = destino;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getFechaReserva() { return fechaReserva; }
    public void setFechaReserva(LocalDate fechaReserva) { this.fechaReserva = fechaReserva; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public Long getViajeId() { return viajeId; }
    public void setViajeId(Long viajeId) { this.viajeId = viajeId; }

    public String getOrigen() { return origen; }
    public void setOrigen(String origen) { this.origen = origen; }

    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }
}
