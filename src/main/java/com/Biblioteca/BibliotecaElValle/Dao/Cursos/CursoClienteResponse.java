package com.Biblioteca.BibliotecaElValle.Dao.Cursos;

import lombok.Data;

import java.io.Serializable;

@Data
public class CursoClienteResponse implements Serializable {

    private String genero;

    private String curso;
}
