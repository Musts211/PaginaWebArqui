package com.miempresa.sistema.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

@Entity
public class Autobus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Pattern(regexp = "^[A-Z]{2}-\\d{4}$", message = "Formato inválido. Ejemplo: AA-1234")
    private String placa;

    private int capacidad;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }
}
