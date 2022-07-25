package com.Biblioteca.BibliotecaElValle.Controller;


import com.Biblioteca.BibliotecaElValle.Dao.Cursos.CursoRequest;
import com.Biblioteca.BibliotecaElValle.Dao.Cursos.CursoResponse;
import com.Biblioteca.BibliotecaElValle.Repository.Curso.CursoClienteConsultaResponse;
import com.Biblioteca.BibliotecaElValle.Service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/curso")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping("/registrarCurso")
    public ResponseEntity<?> registroCurso(@RequestBody CursoRequest request){
        return new ResponseEntity<>(cursoService.registrarCurso(request), HttpStatus.OK);
    }


    @PostMapping("/agregar/{idCliente}/{idCurso}")
    public ResponseEntity<?> agregar(@PathVariable Long idCliente, @PathVariable Long idCurso){

        return new ResponseEntity<>( cursoService.agregarClienteCurso(idCliente,idCurso), HttpStatus.OK);
    }

    @GetMapping("/allCursos")
    public ResponseEntity<List<CursoResponse>> listAllCursos(){
        List<CursoResponse> curso = cursoService.listAllCursos();
        return new ResponseEntity<List<CursoResponse>>(curso, HttpStatus.OK);
    }

    @GetMapping("/consultaClienteCurso/{mes}/{anio}")
    public ResponseEntity<List<CursoClienteConsultaResponse>> listAllCursos(@PathVariable Long mes,@PathVariable Long  anio){
        List<CursoClienteConsultaResponse> curso = cursoService.listaPorMesAndAnio(mes, anio);
        return new ResponseEntity<>(curso, HttpStatus.OK);
    }
}
