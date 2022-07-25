package com.Biblioteca.BibliotecaElValle.Dao.Evento;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class EventoResponse implements Serializable {



    private String descripcion;


    private Long participantes;

    public EventoResponse(String descripcion, Long participantes) {

        this.descripcion = descripcion;

        this.participantes = participantes;
    }

    public EventoResponse() {

    }
}
