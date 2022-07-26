package com.Biblioteca.BibliotecaElValle.Dao.Estadisticas;

import lombok.Data;

import java.io.Serializable;

@Data
public class FiltrarDiscapacidadResponse implements Serializable {

    private Long mes;

    private Long anio;

    private Long total;

    private Datos no;

    private Datos si;
}
