package com.Biblioteca.BibliotecaElValle.Dao.Cursos;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CursoRequest implements Serializable {

    private Long id;

    private String nombre;

    private String responsable;

    private Date fechaInicio;

    private Date fechaFin;
}
