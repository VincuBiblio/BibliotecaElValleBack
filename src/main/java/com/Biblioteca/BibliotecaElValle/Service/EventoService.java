package com.Biblioteca.BibliotecaElValle.Service;


import com.Biblioteca.BibliotecaElValle.Dao.Evento.EventoRequest;
import com.Biblioteca.BibliotecaElValle.Dao.Evento.EventoResponse;
import com.Biblioteca.BibliotecaElValle.Excepciones.BadRequestException;
import com.Biblioteca.BibliotecaElValle.Models.Eventos.Eventos;
import com.Biblioteca.BibliotecaElValle.Repository.Evento.EventoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public boolean registrarEvento(EventoRequest eventoRequest){
        Eventos newEvento = new Eventos();
        newEvento.setDescripcion(eventoRequest.getDescripcion());
        newEvento.setDia((long)eventoRequest.getFecha().getDate()+1);
        newEvento.setMes((long)eventoRequest.getFecha().getMonth()+1);
        newEvento.setAnio((long)eventoRequest.getFecha().getYear()+1900);
        newEvento.setParticipantes(eventoRequest.getParticipantes());
        try{
            Eventos response = eventoRepository.save(newEvento);
            if(response!=null){
                return true;
            }else{
                throw new BadRequestException("No se registró el evento ");
            }

        }catch (Exception e){
            throw new BadRequestException("No se registró el evento " +e);
        }

    }


    @Transactional
    public List<EventoResponse> listaEventosByMesAndAnio(Long mes, Long anio){
        List<Eventos> result = eventoRepository.findByMesAndAnio(mes, anio);
        return result.stream().map(eventos -> {
            EventoResponse resultE = new EventoResponse();
            resultE.setDescripcion(eventos.getDescripcion());
            resultE.setParticipantes(eventos.getParticipantes());
            return resultE;
        }).collect(Collectors.toList());
    }
}
