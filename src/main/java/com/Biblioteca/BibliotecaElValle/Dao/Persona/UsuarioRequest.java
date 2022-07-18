package com.Biblioteca.BibliotecaElValle.Dao.Persona;

import lombok.Data;

import java.io.Serializable;

@Data
public class UsuarioRequest implements Serializable {

    private String email, clave;
}
