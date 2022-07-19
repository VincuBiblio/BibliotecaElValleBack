package com.Biblioteca.BibliotecaElValle.Models.Ubicacion;

import com.Biblioteca.BibliotecaElValle.Models.Persona.Cliente;
import com.Biblioteca.BibliotecaElValle.Models.Persona.Persona;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Table(name = "ubicacion")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ubicacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String barrio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="canton_id", referencedColumnName = "id")
    private Canton canton;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="parroquia_id", referencedColumnName = "id")
    private Parroquia parroquia;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="provincia_id", referencedColumnName = "id")
    private Provincia provincia;

    @OneToMany (targetEntity = Persona.class, mappedBy = "ubicacion")
    private  List<Persona> personas;
}
