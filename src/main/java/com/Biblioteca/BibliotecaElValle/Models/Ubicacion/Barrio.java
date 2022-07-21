package com.Biblioteca.BibliotecaElValle.Models.Ubicacion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Table(name = "barrio")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Barrio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String barrio;

    @OneToOne(mappedBy = "barrio")
    private Ubicacion ubicacion;
}
