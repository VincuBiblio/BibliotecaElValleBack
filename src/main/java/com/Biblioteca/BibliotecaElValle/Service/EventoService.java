package com.Biblioteca.BibliotecaElValle.Service;


import com.Biblioteca.BibliotecaElValle.Dao.Evento.EventoRequest;
import com.Biblioteca.BibliotecaElValle.Dao.Evento.EventoResponse;
import com.Biblioteca.BibliotecaElValle.Excepciones.BadRequestException;
import com.Biblioteca.BibliotecaElValle.Models.Eventos.Eventos;
import com.Biblioteca.BibliotecaElValle.Repository.Evento.EventoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public EventoResponse registrarEvento(EventoRequest eventoRequest){
        Eventos newEvento = new Eventos();
        newEvento.setDescripcion(eventoRequest.getDescripcion());
        newEvento.setFecha(eventoRequest.getFecha());
        newEvento.setParticipantes(eventoRequest.getParticipantes());
        try{
            Eventos response = eventoRepository.save(newEvento);
            return new EventoResponse(response.getId(),response.getDescripcion(), response.getFecha(), response.getParticipantes());
        }catch (Exception e){
            throw new BadRequestException("No se registró el evento " +e);
        }

    }
}
