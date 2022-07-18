package com.Biblioteca.BibliotecaElValle.Repository.ServicioCliente;

import com.Biblioteca.BibliotecaElValle.Models.Servicio.ServicioCliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicioClienteRepository extends JpaRepository<ServicioCliente, Long> {
}
