package com.Biblioteca.BibliotecaElValle.Models.Persona;

import com.Biblioteca.BibliotecaElValle.Models.Cursos.Curso;
import com.Biblioteca.BibliotecaElValle.Models.Servicio.Servicio;
import com.Biblioteca.BibliotecaElValle.Models.Servicio.ServicioCliente;
import com.Biblioteca.BibliotecaElValle.Models.Ubicacion.Ubicacion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Table(name = "cliente")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "estado_civil")
    private String estadoCivil;

    private Boolean discapacidad;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="persona_id", referencedColumnName = "id")
    private Persona persona;

    @OneToMany (targetEntity = ServicioCliente.class, mappedBy = "cliente")
    private List<ServicioCliente> servicioCliente;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "cursos_clientes",
            joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id")
    )
    private List<Curso> cursos = new ArrayList<>();

}
