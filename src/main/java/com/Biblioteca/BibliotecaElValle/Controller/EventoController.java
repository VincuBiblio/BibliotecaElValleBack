package com.Biblioteca.BibliotecaElValle.Controller;


import com.Biblioteca.BibliotecaElValle.Dao.Evento.EventoRequest;
import com.Biblioteca.BibliotecaElValle.Dao.Evento.EventoResponse;
import com.Biblioteca.BibliotecaElValle.Service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/evento")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @PostMapping("/registroEvento")
    public ResponseEntity<?> registroEvento(@RequestBody EventoRequest eventoRequest){
        Boolean response = eventoService.registrarEvento(eventoRequest);
        if(response==null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/consultaAllEventos/{mes}/{anio}")
    public ResponseEntity<List<EventoResponse>> listAllEventosByMesAndAnio(@PathVariable Long mes, @PathVariable Long  anio){
        List<EventoResponse> evento = eventoService.listaEventosByMesAndAnio(mes, anio);
        return new ResponseEntity<>(evento, HttpStatus.OK);
    }
}
