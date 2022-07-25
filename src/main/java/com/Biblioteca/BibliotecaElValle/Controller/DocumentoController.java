package com.Biblioteca.BibliotecaElValle.Controller;


import com.Biblioteca.BibliotecaElValle.Dao.Documento.DocumentoRequest;
import com.Biblioteca.BibliotecaElValle.Dao.Documento.DocumentoResponse;
import com.Biblioteca.BibliotecaElValle.Service.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/documento")
public class DocumentoController {

    @Autowired
    private DocumentoService documentoService;

    @PostMapping("/registroDocuemnto")
    public ResponseEntity<DocumentoResponse> registroDocuemnto(@RequestBody DocumentoRequest documentoRequest){
        DocumentoResponse response = documentoService.registrarDocumento(documentoRequest);
        if(response ==null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
