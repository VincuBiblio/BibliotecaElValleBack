package com.Biblioteca.BibliotecaElValle.Repository.Curso;

import com.Biblioteca.BibliotecaElValle.Models.Cursos.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso,Long> {
}
