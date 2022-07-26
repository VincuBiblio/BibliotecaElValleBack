package com.Biblioteca.BibliotecaElValle.Repository.ServicioCliente;

import com.Biblioteca.BibliotecaElValle.Models.Servicio.ServicioCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServicioClienteRepository extends JpaRepository<ServicioCliente, Long> {

    List<ServicioCliente> findByMesAndAnio(Long mes, Long anio);

    @Query(value = "SELECT count(*)\n" +
            "FROM servicio s, cliente cl, servicio_cliente sc \n" +
            "where sc.mes  = :mes and sc.anio  = :anio and sc.id_servicio = s.id and sc.id_cliente = cl.id", nativeQuery = true)
    Long countByMesAndAnio(Long mes, Long anio);

}
