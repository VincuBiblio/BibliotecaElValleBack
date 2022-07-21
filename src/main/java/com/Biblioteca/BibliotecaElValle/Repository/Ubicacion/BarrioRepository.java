package com.Biblioteca.BibliotecaElValle.Repository.Ubicacion;

import com.Biblioteca.BibliotecaElValle.Models.Ubicacion.Barrio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BarrioRepository extends JpaRepository<Barrio, Long> {

    Optional<Barrio> findByBarrioLikeIgnoreCase(String barrio);
    List<Barrio> findAllById(Long id);
}
