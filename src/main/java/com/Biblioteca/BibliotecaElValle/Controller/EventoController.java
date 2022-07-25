package com.Biblioteca.BibliotecaElValle.Controller;


import com.Biblioteca.BibliotecaElValle.Dao.Evento.EventoRequest;
import com.Biblioteca.BibliotecaElValle.Dao.Evento.EventoResponse;
import com.Biblioteca.BibliotecaElValle.Service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/evento")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @PostMapping("/registroEvento")
    public ResponseEntity<EventoResponse> registroEvento(@RequestBody EventoRequest eventoRequest){
        EventoResponse response = eventoService.registrarEvento(eventoRequest);
        if(response==null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
