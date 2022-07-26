package com.Biblioteca.BibliotecaElValle.Dao.Estadisticas;

import lombok.Data;

import java.io.Serializable;

@Data
public class Datos implements Serializable {
    private Long num;

    private Double pct;
}
