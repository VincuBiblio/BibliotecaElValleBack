package com.Biblioteca.BibliotecaElValle.Controller;


import com.Biblioteca.BibliotecaElValle.Dao.Persona.PersonaClienteRequest;
import com.Biblioteca.BibliotecaElValle.Dao.Persona.PersonaClienteResponse;
import com.Biblioteca.BibliotecaElValle.Dao.Persona.PersonaUsuarioRequest;
import com.Biblioteca.BibliotecaElValle.Dao.Persona.PersonaUsuarioResponse;
import com.Biblioteca.BibliotecaElValle.Service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/persona")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @PostMapping("/registroCliente")
    public ResponseEntity<PersonaClienteResponse> registroCliente(@RequestBody PersonaClienteRequest personaRequest){
        PersonaClienteResponse personaResponse= personaService.registrarCliente(personaRequest);

        if (personaResponse == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(personaResponse, HttpStatus.CREATED);
    }

    @PostMapping("/registroUsuario")
    public ResponseEntity<PersonaUsuarioResponse> registroUsuario(@RequestBody PersonaUsuarioRequest personaRequest){
        PersonaUsuarioResponse personaResponse= personaService.registrarUsuario(personaRequest);

        if (personaResponse == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(personaResponse, HttpStatus.CREATED);
    }
}
