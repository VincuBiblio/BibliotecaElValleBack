package com.Biblioteca.BibliotecaElValle.Dao.Estadisticas;

import lombok.Data;

import java.io.Serializable;

@Data
public class FiltrarServiciosResponse implements Serializable {

    private Long mes;

    private Long anio;

    private Long total;

    private Datos repositorio;

    private Datos biblioteca;

    private Datos internet;

    private Datos imprecopias;

    private Datos tallactv;
}
