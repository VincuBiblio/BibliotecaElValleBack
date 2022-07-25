package com.Biblioteca.BibliotecaElValle.Dao.Documento;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class DocumentoResponse implements Serializable {

    private Long id;

    private String docx;

    private Date fecha;

    public DocumentoResponse(Long id, String docx, Date fecha) {
        this.id = id;
        this.docx = docx;
        this.fecha = fecha;
    }
}
