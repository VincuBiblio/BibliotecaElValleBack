package com.Biblioteca.BibliotecaElValle.Repository.ServicioCliente;

import com.Biblioteca.BibliotecaElValle.Models.Servicio.ServicioCliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicioClienteRepository extends JpaRepository<ServicioCliente, Long> {

    List<ServicioCliente> findByMesAndAnio(Long mes, Long anio);
}
