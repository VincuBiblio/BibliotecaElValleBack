package com.Biblioteca.BibliotecaElValle.Models.Servicio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Table(name = "servicio")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Servicio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String observacion;

    private String descripcion;

    @OneToMany (targetEntity = ServicioCliente.class, mappedBy = "servicio")
    private List<ServicioCliente> servicioCliente;




}
