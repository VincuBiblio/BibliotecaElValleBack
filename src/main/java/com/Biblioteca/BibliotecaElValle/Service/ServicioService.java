package com.Biblioteca.BibliotecaElValle.Service;

import com.Biblioteca.BibliotecaElValle.Dao.Servicio.ServicioRequest;
import com.Biblioteca.BibliotecaElValle.Dao.Servicio.ServicioResponse;
import com.Biblioteca.BibliotecaElValle.Excepciones.BadRequestException;
import com.Biblioteca.BibliotecaElValle.Models.Servicio.Servicio;
import com.Biblioteca.BibliotecaElValle.Repository.Servicio.ServicioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    @Transactional
    public boolean registrarServicio(ServicioRequest servicioRequest){
        Optional<Servicio> optionalServicio = servicioRepository.findByNombreLikeIgnoreCase(servicioRequest.getNombre());
        if(!optionalServicio.isPresent()){
            Servicio newServicio = new Servicio();
            newServicio.setNombre(servicioRequest.getNombre());
            newServicio.setObservacion(servicioRequest.getObservacion());
            newServicio.setDescripcion(servicioRequest.getDescripcion());
            try{
                servicioRepository.save(newServicio);
                return true;
            }catch (Exception ex) {
                throw new BadRequestException("No se guardó el servicio" + ex);
            }
        }else{
            throw new BadRequestException("Ya existe un servicio con el mismo nombre");
        }
    }

    public boolean updateServicio(ServicioRequest servicioRequest){
        Optional<Servicio> optionalServicio = servicioRepository.findById(servicioRequest.getId());
        if(optionalServicio.isPresent()){
            optionalServicio.get().setNombre(servicioRequest.getNombre());
            optionalServicio.get().setObservacion(servicioRequest.getObservacion());
            optionalServicio.get().setDescripcion(servicioRequest.getDescripcion());
            try{
                servicioRepository.save(optionalServicio.get());
                return true;
            }catch (Exception ex) {
                throw new BadRequestException("No se guardó el servicio" + ex);
            }
        }else{
            throw new BadRequestException("No existe el servicio" + servicioRequest.getId());

        }
    }

    public List<ServicioResponse> listAllServicios() {
        List<Servicio> lista = servicioRepository.findAll();
        return lista.stream().map(servicio -> {
            ServicioResponse sr= new ServicioResponse();
            sr.setId(servicio.getId());
            sr.setNombre(servicio.getNombre());
            sr.setDescripcion(servicio.getDescripcion());
            sr.setObservacion(servicio.getObservacion());
            return sr;
        }).collect(Collectors.toList());
    }

    public ServicioResponse listServicioById(Long id){
        ServicioResponse sr= new ServicioResponse();
        Optional<Servicio> optional = servicioRepository.findById(id);
        if(optional.isPresent()){
            sr.setId(optional.get().getId());
            sr.setNombre(optional.get().getNombre());
            sr.setDescripcion(optional.get().getDescripcion());
            sr.setObservacion(optional.get().getObservacion());
            return  sr;
        }else{
            throw new BadRequestException("No existe el servicio" + id);

        }
    }

    @Transactional
    public ServicioResponse listServicioByNombre(String nombre){
        ServicioResponse sr= new ServicioResponse();
        Optional<Servicio> optional = servicioRepository.findByNombre(nombre);
        if(optional.isPresent()){
            sr.setId(optional.get().getId());
            sr.setNombre(optional.get().getNombre());
            sr.setDescripcion(optional.get().getDescripcion());
            sr.setObservacion(optional.get().getObservacion());
            return  sr;
        }else{
            throw new BadRequestException("No existe el servicio con nombre" + nombre);

        }
    }

}
