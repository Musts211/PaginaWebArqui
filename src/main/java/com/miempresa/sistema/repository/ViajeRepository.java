package com.miempresa.sistema.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import com.miempresa.sistema.model.Viaje;
import java.time.LocalDate;
import java.util.List;


public interface ViajeRepository extends JpaRepository<Viaje, Long> {

    @Query("SELECT v FROM Viaje v WHERE " +
            "(:fecha IS NULL OR v.fecha = :fecha) AND " +
            "(:origen IS NULL OR LOWER(v.origen) LIKE LOWER(CONCAT('%', :origen, '%'))) AND " +
            "(:destino IS NULL OR LOWER(v.destino) LIKE LOWER(CONCAT('%', :destino, '%')))")
    List<Viaje> buscarConFiltros(@Param("fecha") LocalDate fecha,
                                 @Param("origen") String origen,
                                 @Param("destino") String destino);


}