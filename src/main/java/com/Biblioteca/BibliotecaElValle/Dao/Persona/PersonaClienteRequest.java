package com.Biblioteca.BibliotecaElValle.Dao.Persona;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PersonaClienteRequest implements Serializable {


    private String cedula;

    private String apellidos;

    private String nombres;

    private Date fechaNacimiento;

    private Long edad;

    private String genero;

    private String telefono;

    private String email;

    private String estadoCivil;

    private Boolean discapacidad;

    private String barrio;

    private Long idCanton;

    private Long idProvincia;

    private Long idParroquia;
}
