package com.Biblioteca.BibliotecaElValle.Repository.Persona;

import com.Biblioteca.BibliotecaElValle.Models.Persona.Persona;
import com.Biblioteca.BibliotecaElValle.Models.Persona.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByPersona (Persona persona);
}
