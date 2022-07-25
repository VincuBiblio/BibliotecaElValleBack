package com.Biblioteca.BibliotecaElValle.Dao.Evento;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class EventoRequest implements Serializable {

    private String descripcion;

    private Date fecha;

    private Long participantes;
}
