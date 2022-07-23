package com.Biblioteca.BibliotecaElValle.Service;


import com.Biblioteca.BibliotecaElValle.Dao.Cursos.CursoRequest;
import com.Biblioteca.BibliotecaElValle.Dao.Cursos.CursoResponse;
import com.Biblioteca.BibliotecaElValle.Excepciones.BadRequestException;
import com.Biblioteca.BibliotecaElValle.Models.Cursos.Curso;
import com.Biblioteca.BibliotecaElValle.Models.Persona.Cliente;
import com.Biblioteca.BibliotecaElValle.Repository.Curso.CursoRepository;
import com.Biblioteca.BibliotecaElValle.Repository.Persona.ClienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;;

    @Autowired
    private ClienteRepository clienteRepository;


    @Transactional
    public boolean registrarCurso (CursoRequest cursoRequest){
        Curso newCurso = new Curso();
        newCurso.setNombre(cursoRequest.getNombre());
        newCurso.setResponsable(cursoRequest.getResponsable());
        newCurso.setFechaInicio(cursoRequest.getFechaInicio());
        newCurso.setFechaFin(cursoRequest.getFechaFin());
        try{
            cursoRepository.save(newCurso);
            return true;
        }catch (Exception ex){
            throw new BadRequestException("No se guardó el curso" + ex);
        }
    }



    @Transactional
    public boolean agregarClienteCurso(Long idCliente, Long idCurso){
        Optional<Cliente> cliente = clienteRepository.findById(idCliente);
        if(cliente.isPresent()) {
            Optional<Curso> curso = cursoRepository.findById(idCurso);
            if(curso.isPresent()) {
                try{
                    cliente.get().getCursos().add(curso.get());
                    clienteRepository.save(cliente.get());
                    return true;
                }catch (Exception e){
                    throw new BadRequestException("No se guardo el cliente en el curso");
                }

            }else{
                throw new BadRequestException("No existe un curso con id" +idCurso);
            }
        }else{
            throw new BadRequestException("No existe un cliente con id" +idCliente);
        }
    }

    //LISTAR TODOS LOS CURSOS
        public List<CursoResponse> listAllCursos(){
            List<Curso> curso = cursoRepository.findAll();
            return curso.stream().map(cursoRequest->{
                CursoResponse cr= new CursoResponse();
                cr.setId(cursoRequest.getId());
                cr.setNombre(cursoRequest.getNombre());
                cr.setResponsable(cursoRequest.getResponsable());
                cr.setFechaInicio(cursoRequest.getFechaInicio());
                cr.setFechaFin(cursoRequest.getFechaFin());
                return  cr;
            }).collect(Collectors.toList());
        }
}
