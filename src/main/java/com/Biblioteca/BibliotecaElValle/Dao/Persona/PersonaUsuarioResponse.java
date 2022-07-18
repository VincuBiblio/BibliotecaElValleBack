package com.Biblioteca.BibliotecaElValle.Dao.Persona;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PersonaUsuarioResponse implements Serializable {

    private Long id;

    private String cedula;

    private String apellidos;

    private String nombres;

    private Date fechaNacimiento;

    private Long edad;

    private String genero;

    private String telefono;

    private String email;

    private String clave;



    public PersonaUsuarioResponse(Long id, String cedula, String apellidos, String nombres, Date fechaNacimiento, Long edad, String genero, String telefono, String email, String clave) {
        this.id = id;
        this.cedula = cedula;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.genero = genero;
        this.telefono = telefono;
        this.email = email;
        this.clave = clave;

    }

}
