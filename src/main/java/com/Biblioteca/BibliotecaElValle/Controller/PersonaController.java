package com.Biblioteca.BibliotecaElValle.Controller;


import com.Biblioteca.BibliotecaElValle.Dao.Persona.*;
import com.Biblioteca.BibliotecaElValle.Excepciones.Mensaje;
import com.Biblioteca.BibliotecaElValle.Service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/persona")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @PostMapping("/registroCliente")
    public ResponseEntity<PersonaClienteResponse> registroCliente(@RequestBody PersonaClienteRequest personaRequest) {
        PersonaClienteResponse personaResponse = personaService.registrarCliente(personaRequest);

        if (personaResponse == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(personaResponse, HttpStatus.CREATED);
    }

    @PostMapping("/registroUsuario")
    public ResponseEntity<PersonaUsuarioResponse> registroUsuario(@RequestBody PersonaUsuarioRequest personaRequest) throws Exception {
        PersonaUsuarioResponse personaResponse = personaService.registrarUsuario(personaRequest);

        if (personaResponse == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(personaResponse, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioRequest request) throws Exception {
        PersonaUsuarioResponse response = personaService.login(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody PersonaClienteRequest request) {
        personaService.updateCliente(request);
        return new ResponseEntity(new Mensaje("Cliente Actualizado"), HttpStatus.OK);
    }

    //Listar todos los clientes
    @GetMapping("/allClientes")
    public ResponseEntity<List<PersonaClienteResponse>> listAllClientes() {
        List<PersonaClienteResponse> clientes = personaService.listAllClientes();
        return new ResponseEntity<List<PersonaClienteResponse>>(clientes, HttpStatus.OK);
    }

    @GetMapping("/cliente/{cedula}")
    public ResponseEntity<PersonaClienteResponse> listClienteByCedula(@PathVariable String cedula){
        PersonaClienteResponse cliente = personaService.ClienteByCedula(cedula);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }
}
