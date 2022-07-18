package com.Biblioteca.BibliotecaElValle.Controller;


import com.Biblioteca.BibliotecaElValle.Dao.Servicio.ServicioRequest;
import com.Biblioteca.BibliotecaElValle.Dao.Servicio.ServicioResponse;
import com.Biblioteca.BibliotecaElValle.Excepciones.Mensaje;
import com.Biblioteca.BibliotecaElValle.Service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/servicio")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ServicioRequest servicioRequest){
        servicioService.registrarServicio(servicioRequest);
        return new ResponseEntity(new Mensaje("Servicio Creado"), HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody ServicioRequest servicioRequest){
        servicioService.updateServicio(servicioRequest);
        return new ResponseEntity(new Mensaje("Servicio Actualizado"), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<ServicioResponse>>listServicios(){
        List<ServicioResponse> servicios =servicioService.listAllServicios();
        return new ResponseEntity<List<ServicioResponse>>(servicios,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicioResponse> listServicioId(@PathVariable Long id) {
        ServicioResponse ser = servicioService.listServicioById(id);
        return new ResponseEntity<>(ser, HttpStatus.OK);
    }
}
