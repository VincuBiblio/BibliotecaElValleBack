package com.Biblioteca.BibliotecaElValle.Models.Documento;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "documento")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Documento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10485760)
    private String docx;

    private Date fecha;
}
