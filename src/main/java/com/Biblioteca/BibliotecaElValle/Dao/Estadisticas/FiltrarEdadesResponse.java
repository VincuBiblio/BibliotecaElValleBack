package com.Biblioteca.BibliotecaElValle.Dao.Estadisticas;

import java.io.Serializable;

public class FiltrarEdadesResponse implements Serializable {

    private Long mes;

    private Long anio;

    private Long total;

    private Datos infantes;

    private Datos ninos;

    private Datos adolescentes;

    private Datos jovenes;

    private Datos adultos;

    private Datos adultosmayores;


}


 class Datos{

    private Long num;

    private Long pct;
}
