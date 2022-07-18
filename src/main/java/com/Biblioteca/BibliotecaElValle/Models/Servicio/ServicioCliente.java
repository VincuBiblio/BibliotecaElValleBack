package com.Biblioteca.BibliotecaElValle.Models.Servicio;

import com.Biblioteca.BibliotecaElValle.Models.Persona.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "servicio_cliente")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServicioCliente implements Serializable {


    @Column(name = "fecha_uso")
    @Temporal(TemporalType.DATE)
    private Date fechaUso;

    @Id
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_cliente",referencedColumnName = "id")
    private Cliente cliente;

    @Id
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_servicio",referencedColumnName = "id")
    private Servicio servicio;

}
