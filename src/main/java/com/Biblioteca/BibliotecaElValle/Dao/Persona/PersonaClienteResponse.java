package com.Biblioteca.BibliotecaElValle.Dao.Persona;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PersonaClienteResponse implements Serializable {

    private Long id;

    private Long idCliente;

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

    private Long idBarrio;
    private String barrio;

    private Long idParroquia;
    private String parroquia;


    private Long idCanton;
    private String canton;

    private Long idProvincia;
    private String provincia;

    public PersonaClienteResponse(Long id, String cedula, String apellidos, String nombres, Date fechaNacimiento, Long edad, String genero, String telefono, String email) {
        this.id = id;
        this.cedula = cedula;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.genero = genero;
        this.telefono = telefono;
        this.email = email;
    }

    public PersonaClienteResponse(Long id, String cedula, String apellidos, String nombres, Date fechaNacimiento, Long edad, String genero, String telefono, String email, String estadoCivil, Boolean discapacidad) {
        this.id = id;
        this.cedula = cedula;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.genero = genero;
        this.telefono = telefono;
        this.email = email;
        this.estadoCivil = estadoCivil;
        this.discapacidad = discapacidad;
    }

    public PersonaClienteResponse(Long id, String cedula, String apellidos, String nombres, Date fechaNacimiento, Long edad, String genero, String telefono, String email, String estadoCivil, Boolean discapacidad, String barrio, String parroquia, String canton, String provincia) {
        this.id = id;
        this.cedula = cedula;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.genero = genero;
        this.telefono = telefono;
        this.email = email;
        this.estadoCivil = estadoCivil;
        this.discapacidad = discapacidad;
        this.barrio = barrio;
        this.parroquia = parroquia;
        this.canton = canton;
        this.provincia = provincia;
    }


    public PersonaClienteResponse(Long id, Long idCliente, String cedula, String apellidos, String nombres, Date fechaNacimiento, Long edad, String genero, String telefono, String email, String estadoCivil, Boolean discapacidad, Long idBarrio, String barrio, Long idParroquia, String parroquia, Long idCanton, String canton, Long idProvincia, String provincia) {
        this.id = id;
        this.idCliente = idCliente;
        this.cedula = cedula;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.genero = genero;
        this.telefono = telefono;
        this.email = email;
        this.estadoCivil = estadoCivil;
        this.discapacidad = discapacidad;
        this.idBarrio = idBarrio;
        this.barrio = barrio;
        this.idParroquia = idParroquia;
        this.parroquia = parroquia;
        this.idCanton = idCanton;
        this.canton = canton;
        this.idProvincia = idProvincia;
        this.provincia = provincia;
    }

    public PersonaClienteResponse() {
    }
}
