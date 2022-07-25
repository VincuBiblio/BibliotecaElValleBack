package com.Biblioteca.BibliotecaElValle.Repository.Evento;

import com.Biblioteca.BibliotecaElValle.Models.Eventos.Eventos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventoRepository extends JpaRepository<Eventos, Long> {

    List<Eventos> findByMesAndAnio(Long mes, Long anio);
}
