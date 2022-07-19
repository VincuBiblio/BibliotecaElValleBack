package com.Biblioteca.BibliotecaElValle.Models.Persona;

import com.Biblioteca.BibliotecaElValle.Models.Ubicacion.Ubicacion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "persona")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Persona implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cedula;

    private String apellidos;

    private String nombres;

    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    private Long edad;

    private String genero;

    private String telefono;

    private String email;

    @OneToOne(mappedBy = "persona")
    private Cliente clientes;

    @OneToOne(mappedBy = "persona")
    private Usuario usuarios;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_ubicacion",referencedColumnName = "id")
    private Ubicacion ubicacion;
}
