package com.Biblioteca.BibliotecaElValle.Service;


import com.Biblioteca.BibliotecaElValle.Dao.Estadisticas.Datos;
import com.Biblioteca.BibliotecaElValle.Dao.Estadisticas.FiltrarEdadesResponse;
import com.Biblioteca.BibliotecaElValle.Dao.ServicioCliente.ServicioClienteListResponse;
import com.Biblioteca.BibliotecaElValle.Dao.ServicioCliente.ServicioClienteRequest;
import com.Biblioteca.BibliotecaElValle.Excepciones.BadRequestException;
import com.Biblioteca.BibliotecaElValle.Models.Persona.Cliente;
import com.Biblioteca.BibliotecaElValle.Models.Servicio.Servicio;
import com.Biblioteca.BibliotecaElValle.Models.Servicio.ServicioCliente;
import com.Biblioteca.BibliotecaElValle.Repository.Persona.ClienteRepository;
import com.Biblioteca.BibliotecaElValle.Repository.Persona.PersonaRepository;
import com.Biblioteca.BibliotecaElValle.Repository.Servicio.ServicioRepository;
import com.Biblioteca.BibliotecaElValle.Repository.ServicioCliente.ServicioClienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ServicioClienteService {
    @Autowired
    private ServicioClienteRepository servicioClienteRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Transactional
    public boolean registrarServicioCliente (ServicioClienteRequest servicioClienteRequest){
        Optional<Cliente> cliente = clienteRepository.findById(servicioClienteRequest.getIdCliente());
        if(cliente.isPresent()){
            Optional<Servicio> servicio = servicioRepository.findById(servicioClienteRequest.getIdServicio());
            if(servicio.isPresent()){
                ServicioCliente newServicioCliente = new ServicioCliente();

                newServicioCliente.setDia((long) servicioClienteRequest.getFechaUso().getDate()+1);
                newServicioCliente.setMes((long) servicioClienteRequest.getFechaUso().getMonth()+1);
                newServicioCliente.setAnio((long) servicioClienteRequest.getFechaUso().getYear()+1900);
                newServicioCliente.setCliente(cliente.get());
                newServicioCliente.setServicio(servicio.get());
                try{
                    servicioClienteRepository.save(newServicioCliente);
                    return true;
                }catch (Exception ex) {
                    throw new BadRequestException("No se guardó el cliente/servicio" + ex);
                }

            }else{
                throw new BadRequestException("No existe servicio con id" +servicioClienteRequest.getIdServicio());
            }
        }else{
            throw new BadRequestException("No existe cliente con id" +servicioClienteRequest.getIdCliente());
        }
    }

    @Transactional
    public List<ServicioClienteListResponse> listaPorMesAndAño(Long mes, Long anio){


        List<ServicioCliente> lista = servicioClienteRepository.findByMesAndAnio(mes,anio);

        if(!lista.isEmpty()){
            return  lista.stream().map(servicioCliente -> {
                ServicioClienteListResponse response = new ServicioClienteListResponse();
                response.setGenero(servicioCliente.getCliente().getPersona().getGenero());
                response.setServicio(servicioCliente.getServicio().getNombre());
                return response;
            }).collect(Collectors.toList());
        }else{
            throw new BadRequestException("No existe servicios usados en el mes seleccionado");
        }

    }


    @Transactional
    public FiltrarEdadesResponse filtrarEdades(Long mes, Long anio){
                Long total = servicioClienteRepository.countByMesAndAnio(mes, anio);

                Long numInfantes= personaRepository.countByEdadAndEdad((long)0,(long)5);
                Long numNinos= personaRepository.countByEdadAndEdad((long)6,(long)11);
                Long numAdolescentes= personaRepository.countByEdadAndEdad((long)12,(long)17);
                Long numJovenes= personaRepository.countByEdadAndEdad((long)18,(long)25);
                Long numAdultos= personaRepository.countByEdadAndEdad((long)26,(long)64);
                Long numAdultosMayores= personaRepository.countByEdadAndEdad((long)65,(long)150);

                Datos datosInfantes = new Datos();
                datosInfantes.setNum(numInfantes);
                datosInfantes.setPct(calcularPorcentaje(total, numInfantes));

                Datos datosNinos = new Datos();
                datosNinos.setNum(numNinos);
                datosNinos.setPct(calcularPorcentaje(total,numNinos));

                Datos datosAdolescentes = new Datos();
                datosAdolescentes.setNum(numAdolescentes);
                datosAdolescentes.setPct(calcularPorcentaje(total,numAdolescentes));

                Datos datosJovenes = new Datos();
                datosJovenes.setNum(numJovenes);
                datosJovenes.setPct(calcularPorcentaje(total,numJovenes));

                Datos datosAdultos = new Datos();
                datosAdultos.setNum(numAdultos);
                datosAdultos.setPct(calcularPorcentaje(total,numAdultos));

                Datos datosAdultosMayores = new Datos();
                datosAdultosMayores.setNum(numAdultosMayores);
                datosAdultosMayores.setPct(calcularPorcentaje(total,numAdultosMayores));



                FiltrarEdadesResponse response = new FiltrarEdadesResponse();
                response.setMes(mes);
                response.setAnio(anio);
                response.setTotal(total);
                response.setInfantes(datosInfantes);
                response.setNinos(datosNinos);
                response.setAdolescentes(datosAdolescentes);
                response.setJovenes(datosJovenes);
                response.setAdultos(datosAdultos);
                response.setAdultosmayores(datosAdultosMayores);

                return response;

    }


    public Double calcularPorcentaje(Long total, Long cantidad){
        Double pct= (double)(cantidad*100)/total;
        return Math.round(pct*100)/100.0;

    }
}
