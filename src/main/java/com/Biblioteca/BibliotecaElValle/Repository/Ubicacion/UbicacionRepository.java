package com.Biblioteca.BibliotecaElValle.Repository.Ubicacion;

import com.Biblioteca.BibliotecaElValle.Models.Ubicacion.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UbicacionRepository extends JpaRepository<Ubicacion,Long> {

    Optional<Ubicacion> findByBarrioLikeIgnoreCase(String barrio);
}
