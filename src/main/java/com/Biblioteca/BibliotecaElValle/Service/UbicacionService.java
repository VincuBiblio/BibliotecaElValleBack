package com.Biblioteca.BibliotecaElValle.Service;

import com.Biblioteca.BibliotecaElValle.Dao.Ubicacion.CantonResponse;
import com.Biblioteca.BibliotecaElValle.Dao.Ubicacion.ParroquiaResponse;
import com.Biblioteca.BibliotecaElValle.Dao.Ubicacion.ProvinciaResponse;
import com.Biblioteca.BibliotecaElValle.Models.Ubicacion.Canton;
import com.Biblioteca.BibliotecaElValle.Models.Ubicacion.Parroquia;
import com.Biblioteca.BibliotecaElValle.Models.Ubicacion.Provincia;
import com.Biblioteca.BibliotecaElValle.Repository.Ubicacion.CantonRepository;
import com.Biblioteca.BibliotecaElValle.Repository.Ubicacion.ParroquiaRepository;
import com.Biblioteca.BibliotecaElValle.Repository.Ubicacion.ProvinciaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UbicacionService {

    @Autowired
    private CantonRepository cantonRepository;

    @Autowired
    private ParroquiaRepository parroquiaRepository;

    @Autowired
    private ProvinciaRepository provinciaRepository;


    //LISTAR TODOS LOS CANTONES

    public List<CantonResponse> listAllCantones(){
        List<Canton> canton = cantonRepository.findAll();
        return canton.stream().map(cantonRequest ->{
            CantonResponse cantonResponse = new CantonResponse();
            cantonResponse.setId(cantonRequest.getId());
            cantonResponse.setCanton(cantonRequest.getCanton());
            cantonResponse.setIdProvincia(cantonRequest.getProvincia().getId());
            return cantonResponse;
        }).collect(Collectors.toList());
    }

    //LISTAR TODAS LAS PARROQUIAS

    public List<ParroquiaResponse> listAllParroquias(){
        List<Parroquia> parroquia = parroquiaRepository.findAll();
        return parroquia.stream().map(parroquiaRequest ->{
            ParroquiaResponse parroquiaResponse = new ParroquiaResponse();
            parroquiaResponse.setId(parroquiaRequest.getId());
            parroquiaResponse.setParroquia(parroquiaRequest.getParroquia());
            parroquiaResponse.setIdCanton(parroquiaRequest.getCanton().getId());
            return parroquiaResponse;
        }).collect(Collectors.toList());
    }


    //LISTAR TODAS LAS PROVINCIAS
    public List<ProvinciaResponse> listAllProvincias(){
        List<Provincia> provincia = provinciaRepository.findAll();
        return provincia.stream().map(provinciaRequest ->{
            ProvinciaResponse provinciaResponse = new ProvinciaResponse();
            provinciaResponse.setId(provinciaRequest.getId());
            provinciaResponse.setProvincia(provinciaRequest.getProvincia());
            return provinciaResponse;
        }).collect(Collectors.toList());
    }

}
