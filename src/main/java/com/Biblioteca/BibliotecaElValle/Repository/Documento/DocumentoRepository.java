package com.Biblioteca.BibliotecaElValle.Repository.Documento;

import com.Biblioteca.BibliotecaElValle.Models.Documento.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {
}
