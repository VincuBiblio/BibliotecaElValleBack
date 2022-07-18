package com.Biblioteca.BibliotecaElValle.Repository.Servicio;

import com.Biblioteca.BibliotecaElValle.Models.Servicio.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServicioRepository extends JpaRepository<Servicio, Long> {

    Optional<Servicio> findByNombreLikeIgnoreCase(String nombre);
}
