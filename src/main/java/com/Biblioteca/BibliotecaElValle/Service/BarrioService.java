package com.Biblioteca.BibliotecaElValle.Service;

import com.Biblioteca.BibliotecaElValle.Dao.Ubicacion.BarrioRequest;
import com.Biblioteca.BibliotecaElValle.Excepciones.BadRequestException;
import com.Biblioteca.BibliotecaElValle.Models.Ubicacion.Barrio;
import com.Biblioteca.BibliotecaElValle.Repository.Ubicacion.BarrioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class BarrioService {

    @Autowired
    private BarrioRepository barrioRepository;

    public boolean regitrarBarrio (BarrioRequest barrioRequest){
        Optional<Barrio> optionalBarrio = barrioRepository.findByBarrioLikeIgnoreCase(barrioRequest.getBarrio());
        if(!optionalBarrio.isPresent()) {
            Barrio newBarrio = new Barrio();
            newBarrio.setBarrio(barrioRequest.getBarrio());
            try{
                barrioRepository.save(newBarrio);
                return true;
            }catch (Exception e){
                throw new BadRequestException("No se registró el barrio" +e);
            }
        }else{
            throw new BadRequestException("Ya existe un barrio con ese nombre");
        }
    }
}
