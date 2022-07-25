package com.Biblioteca.BibliotecaElValle.Dao.Documento;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class DocumentoRequest implements Serializable {

    private String docx;

    private Date fecha;
}
