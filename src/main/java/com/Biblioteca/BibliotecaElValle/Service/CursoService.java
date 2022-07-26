package com.Biblioteca.BibliotecaElValle.Service;


import com.Biblioteca.BibliotecaElValle.Dao.Cursos.CursoClienteResponse;
import com.Biblioteca.BibliotecaElValle.Dao.Cursos.CursoNombreResponse;
import com.Biblioteca.BibliotecaElValle.Dao.Cursos.CursoRequest;
import com.Biblioteca.BibliotecaElValle.Dao.Cursos.CursoResponse;
import com.Biblioteca.BibliotecaElValle.Excepciones.BadRequestException;
import com.Biblioteca.BibliotecaElValle.Models.Cursos.Curso;
import com.Biblioteca.BibliotecaElValle.Models.Persona.Cliente;
import com.Biblioteca.BibliotecaElValle.Repository.Curso.CursoClienteConsultaResponse;
import com.Biblioteca.BibliotecaElValle.Repository.Curso.CursoRepository;
import com.Biblioteca.BibliotecaElValle.Repository.Persona.ClienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        newCurso.setDiaInicio((long)cursoRequest.getFechaInicio().getDate()+1);
        newCurso.setMesInicio((long)cursoRequest.getFechaInicio().getMonth()+1);
        newCurso.setAnioInicio((long)cursoRequest.getFechaInicio().getYear()+1900);
        newCurso.setFechaFin(cursoRequest.getFechaFin());
        try{
            cursoRepository.save(newCurso);
            return true;
        }catch (Exception ex){
            throw new BadRequestException("No se guardó el curso" + ex);
        }
    }

    @Transactional
    public boolean updateCurso(CursoRequest cursoRequest){
        Optional<Curso> curso= cursoRepository.findById(cursoRequest.getId());
        if(curso.isPresent()){
            curso.get().setNombre(cursoRequest.getNombre());
            curso.get().setResponsable(cursoRequest.getResponsable());
            curso.get().setFechaFin(cursoRequest.getFechaFin());
            curso.get().setDiaInicio((long)cursoRequest.getFechaInicio().getDate()+1);
            curso.get().setMesInicio((long)cursoRequest.getFechaInicio().getMonth()+1);
            curso.get().setAnioInicio((long)cursoRequest.getFechaInicio().getYear()+1900);
            try{
                cursoRepository.save(curso.get());
                return true;
            }catch (Exception ex) {
                throw new BadRequestException("No se actualizó el curso" + ex);
            }
        }else {
            throw new BadRequestException("No existe un curso con id "+cursoRequest.getId() );
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
                cr.setFechaInicio(ParseFecha(cursoRequest.getDiaInicio()+"",cursoRequest.getMesInicio()+"",cursoRequest.getAnioInicio()+""));
                cr.setFechaFin(cursoRequest.getFechaFin());
                return  cr;
            }).collect(Collectors.toList());
        }


        public static Date ParseFecha(String dia,String mes,String anio){

        String fecha= dia+"/"+mes+"/"+anio;
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaDate = null;
            try {
                fechaDate = formato.parse(fecha);
            }
            catch (ParseException ex)
            {
                System.out.println(ex);
            }
            return fechaDate;
        }

        @Transactional
    public List<CursoClienteConsultaResponse> listaPorMesAndAnio(Long mes, Long anio){
       return cursoRepository.findDistinctByMesInicioAndAnioInicio(mes, anio);
        }


     @Transactional
    public List<CursoNombreResponse> listaCursosByMesAndAnio(Long mes, Long anio){
        List<Curso> result = cursoRepository.findByMesInicioAndAnioInicio(mes, anio);
        if(!result.isEmpty()){
            return result.stream().map(curso->{
                CursoNombreResponse cursoNombrepo = new CursoNombreResponse();
                cursoNombrepo.setCurso(curso.getNombre());
                return cursoNombrepo;
            }).collect(Collectors.toList());
        }else{
            throw new BadRequestException("No existe ningun curso según la fecha seleccionada");
        }
        }
}
