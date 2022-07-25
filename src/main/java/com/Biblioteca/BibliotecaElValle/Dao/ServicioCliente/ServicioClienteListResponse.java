package com.Biblioteca.BibliotecaElValle.Dao.ServicioCliente;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
public class ServicioClienteListResponse implements Serializable {

    private String genero;

    private String servicio;

    public ServicioClienteListResponse() {
    }
}
