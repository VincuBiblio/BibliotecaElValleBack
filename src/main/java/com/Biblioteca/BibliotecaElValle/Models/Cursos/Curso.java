package com.Biblioteca.BibliotecaElValle.Models.Cursos;

import com.Biblioteca.BibliotecaElValle.Models.Persona.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Table(name = "curso")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Curso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String responsable;

    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    @ManyToMany(mappedBy ="cursos")
    private List<Cliente> clientes = new ArrayList<>();
}
