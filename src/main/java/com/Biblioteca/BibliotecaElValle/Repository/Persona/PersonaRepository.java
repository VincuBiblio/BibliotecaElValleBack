package com.Biblioteca.BibliotecaElValle.Repository.Persona;

import com.Biblioteca.BibliotecaElValle.Models.Persona.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona,Long> {

    Boolean existsByCedula (String cedula);

    Optional<Persona> findByCedula(String cedula);
}
