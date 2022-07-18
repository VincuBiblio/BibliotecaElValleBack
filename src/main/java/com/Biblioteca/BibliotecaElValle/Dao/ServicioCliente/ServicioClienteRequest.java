package com.Biblioteca.BibliotecaElValle.Dao.ServicioCliente;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ServicioClienteRequest implements Serializable {

    private Date fechaUso;

    private Long idCliente;

    private Long idServicio;
}
