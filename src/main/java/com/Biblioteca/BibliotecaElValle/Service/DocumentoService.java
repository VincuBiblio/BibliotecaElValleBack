package com.Biblioteca.BibliotecaElValle.Service;


import com.Biblioteca.BibliotecaElValle.Dao.Documento.DocumentoRequest;
import com.Biblioteca.BibliotecaElValle.Dao.Documento.DocumentoResponse;
import com.Biblioteca.BibliotecaElValle.Excepciones.BadRequestException;
import com.Biblioteca.BibliotecaElValle.Models.Documento.Documento;
import com.Biblioteca.BibliotecaElValle.Repository.Documento.DocumentoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DocumentoService {

    @Autowired
    private DocumentoRepository documentoRepository;

    public DocumentoResponse registrarDocumento (DocumentoRequest documentoRequest){
        Documento newDocumento = new Documento();
        newDocumento.setDocx(documentoRequest.getDocx());
        newDocumento.setFecha(documentoRequest.getFecha());
        try{
            Documento response = documentoRepository.save(newDocumento);
            return  new DocumentoResponse(response.getId(), response.getDocx(), response.getFecha());
        }catch (Exception e){
            throw new BadRequestException("No se registró el documento " +e);
        }
    }



}
