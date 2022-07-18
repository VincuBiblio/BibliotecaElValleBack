package com.Biblioteca.BibliotecaElValle.Service;


import com.Biblioteca.BibliotecaElValle.Dao.ServicioCliente.ServicioClienteRequest;
import com.Biblioteca.BibliotecaElValle.Excepciones.BadRequestException;
import com.Biblioteca.BibliotecaElValle.Models.Persona.Cliente;
import com.Biblioteca.BibliotecaElValle.Models.Servicio.Servicio;
import com.Biblioteca.BibliotecaElValle.Models.Servicio.ServicioCliente;
import com.Biblioteca.BibliotecaElValle.Repository.Persona.ClienteRepository;
import com.Biblioteca.BibliotecaElValle.Repository.Servicio.ServicioRepository;
import com.Biblioteca.BibliotecaElValle.Repository.ServicioCliente.ServicioClienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
public class ServicioClienteService {
    @Autowired
    private ServicioClienteRepository servicioClienteRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @Transactional
    public boolean registrarServicioCliente (ServicioClienteRequest servicioClienteRequest){
        Optional<Cliente> cliente = clienteRepository.findById(servicioClienteRequest.getIdCliente());
        if(cliente.isPresent()){
            Optional<Servicio> servicio = servicioRepository.findById(servicioClienteRequest.getIdServicio());
            if(servicio.isPresent()){
                ServicioCliente newServicioCliente = new ServicioCliente();
                newServicioCliente.setFechaUso(servicioClienteRequest.getFechaUso());
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
}
