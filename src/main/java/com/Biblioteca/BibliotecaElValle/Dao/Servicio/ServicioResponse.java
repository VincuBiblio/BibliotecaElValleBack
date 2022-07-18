package com.Biblioteca.BibliotecaElValle.Dao.Servicio;

import lombok.Data;

import java.io.Serializable;

@Data
public class ServicioResponse implements Serializable {

    private Long id;

    private String nombre;

    private String observacion;

    private String descripcion;
}
