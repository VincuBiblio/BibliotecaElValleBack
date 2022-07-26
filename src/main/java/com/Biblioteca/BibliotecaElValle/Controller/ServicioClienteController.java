package com.Biblioteca.BibliotecaElValle.Controller;

import com.Biblioteca.BibliotecaElValle.Dao.Cursos.CursoNombreResponse;
import com.Biblioteca.BibliotecaElValle.Dao.Estadisticas.FiltrarEdadesResponse;
import com.Biblioteca.BibliotecaElValle.Dao.Estadisticas.FiltrarServiciosResponse;
import com.Biblioteca.BibliotecaElValle.Dao.ServicioCliente.ServicioClienteListResponse;
import com.Biblioteca.BibliotecaElValle.Dao.ServicioCliente.ServicioClienteRequest;
import com.Biblioteca.BibliotecaElValle.Excepciones.Mensaje;
import com.Biblioteca.BibliotecaElValle.Service.ServicioClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/servicio/cliente")
public class ServicioClienteController {

    @Autowired
    private ServicioClienteService servicioClienteService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ServicioClienteRequest servicioClienteRequest){
        servicioClienteService.registrarServicioCliente(servicioClienteRequest);
        return new ResponseEntity(new Mensaje("Servicio-Cliente Creado"), HttpStatus.CREATED);
    }

    @GetMapping("/usoServicios/{mes}/{anio}")
    public ResponseEntity<List<ServicioClienteListResponse>> listaUsoServicios(@PathVariable Long mes, @PathVariable Long anio){
        List<ServicioClienteListResponse> allUsoServicios = servicioClienteService.listaPorMesAndAño(mes, anio);
        return new ResponseEntity<>(allUsoServicios, HttpStatus.OK);
    }

    @GetMapping("/filtrarByEdades/{mes}/{anio}")
    public ResponseEntity<FiltrarEdadesResponse> filtrarEstadisticasByMesAndAnio(@PathVariable Long mes, @PathVariable Long  anio){
        FiltrarEdadesResponse filtro = servicioClienteService.filtrarEdades(mes, anio);
        return new ResponseEntity<>(filtro, HttpStatus.OK);
    }

    @GetMapping("/filtrarByServicios/{mes}/{anio}")
    public ResponseEntity<FiltrarServiciosResponse> filtrarServiciosByMesAndAnio(@PathVariable Long mes, @PathVariable Long  anio){
        FiltrarServiciosResponse filtro = servicioClienteService.filtrarServicios(mes, anio);
        return new ResponseEntity<>(filtro, HttpStatus.OK);
    }
}
