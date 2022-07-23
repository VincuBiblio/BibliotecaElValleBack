package com.Biblioteca.BibliotecaElValle.Service;

import com.Biblioteca.BibliotecaElValle.Dao.Ubicacion.BarrioRequest;
import com.Biblioteca.BibliotecaElValle.Dao.Ubicacion.BarrioResponse;
import com.Biblioteca.BibliotecaElValle.Excepciones.BadRequestException;
import com.Biblioteca.BibliotecaElValle.Models.Ubicacion.Barrio;
import com.Biblioteca.BibliotecaElValle.Repository.Ubicacion.BarrioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public BarrioResponse barrioById(Long id){
        Optional<Barrio> barrio = barrioRepository.findById(id);
        if(barrio.isPresent()) {
            BarrioResponse response = new BarrioResponse();
            response.setBarrio(barrio.get().getBarrio());
            return response;
        }else{
            throw new BadRequestException("No existe un barrio con id" +id);
        }
    }



    //LISTAR TODOS LOS BARRIOS
    public List<BarrioResponse> listAllBarrios() {
        List<Barrio> barrio = barrioRepository.findAll();
        return barrio.stream().map(barrioRequest->{
            BarrioResponse response = new BarrioResponse();
            response.setId(barrioRequest.getId());
            response.setBarrio(barrioRequest.getBarrio());
            return response;
        }).collect(Collectors.toList());
    }
}
