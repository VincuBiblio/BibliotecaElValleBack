package com.Biblioteca.BibliotecaElValle.Models.Eventos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "eventos")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Eventos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;

    private Long dia;

    private Long mes;

    private Long anio;

    private Long participantes;
}
