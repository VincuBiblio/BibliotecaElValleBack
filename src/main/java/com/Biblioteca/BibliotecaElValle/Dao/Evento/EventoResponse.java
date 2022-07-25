package com.Biblioteca.BibliotecaElValle.Dao.Evento;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class EventoResponse implements Serializable {

    private Long id;

    private String descripcion;

    private Date fecha;

    private Long participantes;

    public EventoResponse(Long id, String descripcion, Date fecha, Long participantes) {
        this.id = id;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.participantes = participantes;
    }
}
