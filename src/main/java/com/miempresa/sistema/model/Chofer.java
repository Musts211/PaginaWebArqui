package com.miempresa.sistema.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
@Entity
public class Chofer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^CH-\\d{4}$", message = "Código inválido. Ejemplo: CH-1234")
    private String codigo;

    private String nombre;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}
