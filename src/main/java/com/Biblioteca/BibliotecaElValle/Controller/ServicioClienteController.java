package com.Biblioteca.BibliotecaElValle.Controller;

import com.Biblioteca.BibliotecaElValle.Dao.ServicioCliente.ServicioClienteRequest;
import com.Biblioteca.BibliotecaElValle.Excepciones.Mensaje;
import com.Biblioteca.BibliotecaElValle.Service.ServicioClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/servicio/cliente")
public class ServicioClienteController {

    @Autowired
    private ServicioClienteService servicioClienteService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ServicioClienteRequest servicioClienteRequest){
        servicioClienteService.registrarServicioCliente(servicioClienteRequest);
        return new ResponseEntity(new Mensaje("Servicio-Cliente Creado"), HttpStatus.CREATED);
    }
}
