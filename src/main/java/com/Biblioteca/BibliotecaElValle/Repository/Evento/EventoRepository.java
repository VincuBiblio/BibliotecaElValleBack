package com.Biblioteca.BibliotecaElValle.Repository.Evento;

import com.Biblioteca.BibliotecaElValle.Models.Eventos.Eventos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Eventos, Long> {
}
