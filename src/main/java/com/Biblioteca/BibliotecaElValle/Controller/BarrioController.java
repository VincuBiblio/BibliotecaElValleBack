package com.Biblioteca.BibliotecaElValle.Controller;


import com.Biblioteca.BibliotecaElValle.Dao.Ubicacion.BarrioRequest;
import com.Biblioteca.BibliotecaElValle.Service.BarrioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/barrio")
public class BarrioController {

    @Autowired
    private BarrioService barrioService;

    @PostMapping("/registrarBarrio")
    public ResponseEntity<?> registroBarrio(@RequestBody BarrioRequest request){
        return new ResponseEntity<>(barrioService.regitrarBarrio(request), HttpStatus.OK);
    }

}
