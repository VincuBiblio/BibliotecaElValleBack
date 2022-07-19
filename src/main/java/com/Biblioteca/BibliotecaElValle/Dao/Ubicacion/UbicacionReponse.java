package com.Biblioteca.BibliotecaElValle.Dao.Ubicacion;

import lombok.Data;

import java.io.Serializable;

@Data
public class UbicacionReponse implements Serializable {

    private Long id;

    public UbicacionReponse(Long id) {
        this.id = id;
    }
}
