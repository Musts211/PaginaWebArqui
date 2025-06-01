package com.miempresa.sistema.repository;
import com.miempresa.sistema.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByUsuarioId(Long usuarioId);
    long countByViajeId(Long viajeId); // mejora en eficiencia frente a stream().count()
}