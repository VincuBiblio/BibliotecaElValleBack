package com.Biblioteca.BibliotecaElValle.Service;


import com.Biblioteca.BibliotecaElValle.Dao.Persona.*;
import com.Biblioteca.BibliotecaElValle.Excepciones.BadRequestException;
import com.Biblioteca.BibliotecaElValle.Models.Persona.Cliente;
import com.Biblioteca.BibliotecaElValle.Models.Persona.Persona;
import com.Biblioteca.BibliotecaElValle.Models.Persona.Usuario;
import com.Biblioteca.BibliotecaElValle.Repository.Persona.ClienteRepository;
import com.Biblioteca.BibliotecaElValle.Repository.Persona.PersonaRepository;
import com.Biblioteca.BibliotecaElValle.Repository.Persona.UsuarioRepository;
import com.Biblioteca.BibliotecaElValle.Security.jwt.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service
public class PersonaService implements UserDetailsService {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Transactional
    public PersonaClienteResponse registrarCliente(PersonaClienteRequest personaClienteRequest){
        Persona newPersona = new Persona();
        newPersona.setCedula(personaClienteRequest.getCedula());
        newPersona.setApellidos(personaClienteRequest.getApellidos());
        newPersona.setNombres(personaClienteRequest.getNombres());
        newPersona.setFechaNacimiento(personaClienteRequest.getFechaNacimiento());
        newPersona.setEdad(personaClienteRequest.getEdad());
        newPersona.setGenero(personaClienteRequest.getGenero());
        newPersona.setTelefono(personaClienteRequest.getTelefono());
        newPersona.setEmail(personaClienteRequest.getEmail());
        if(!getPersona(personaClienteRequest.getCedula())){
            Persona persona = personaRepository.save(newPersona);
            if(persona!=null){
                guardarCliente(persona.getCedula(), personaClienteRequest.getEstadoCivil(), personaClienteRequest.getDiscapacidad());
                Optional<Cliente> cliente = clienteRepository.findByPersona(persona);
                return new PersonaClienteResponse(persona.getId(),persona.getCedula(),
                         persona.getApellidos(), persona.getNombres(),persona.getFechaNacimiento(),
                        persona.getEdad(),persona.getGenero(), persona.getTelefono(), persona.getEmail(), cliente.get().getEstadoCivil(), cliente.get().getDiscapacidad());
            }else {
                log.error("No se puedo guardar el cliente con cédula: {} e email: {}", personaClienteRequest.getCedula(), personaClienteRequest.getEmail());
                throw new BadRequestException("No se pudo guardar el cliente");
            }
        }else {
            log.error("La cédula ya está registrada: {}", personaClienteRequest.getCedula());
            throw new BadRequestException("La cedula ingresada, ya esta registrada, si la cedula le pertenece contactenos a");
        }
    }
    //Guardar Cliente
    private boolean guardarCliente(String cedula,String estado, Boolean discapacidad){

        Optional<Persona> optionalPersona = personaRepository.findByCedula(cedula);
        if(optionalPersona.isPresent()){
            Persona persona = optionalPersona.get();
            Cliente newCliente = new Cliente();
            newCliente.setEstadoCivil(estado);
            newCliente.setDiscapacidad(discapacidad);
            newCliente.setPersona(persona);
            Cliente cliente = clienteRepository.save(newCliente);
            if(cliente!=null){
                return true;
            }else{
                throw new BadRequestException("Cliente no registrado");

            }

        }else{
            throw new BadRequestException("La cedula ingresada, no está registrada");

        }
    }


    @Transactional
    public PersonaUsuarioResponse registrarUsuario(PersonaUsuarioRequest personaUsuarioRequest) throws Exception {
        Persona newPersona = new Persona();
        newPersona.setCedula(personaUsuarioRequest.getCedula());
        newPersona.setApellidos(personaUsuarioRequest.getApellidos());
        newPersona.setNombres(personaUsuarioRequest.getNombres());
        newPersona.setFechaNacimiento(personaUsuarioRequest.getFechaNacimiento());
        newPersona.setEdad(personaUsuarioRequest.getEdad());
        newPersona.setGenero(personaUsuarioRequest.getGenero());
        newPersona.setTelefono(personaUsuarioRequest.getTelefono());
        newPersona.setEmail(personaUsuarioRequest.getEmail());
        if(!getPersona(personaUsuarioRequest.getCedula())){
            Persona persona = personaRepository.save(newPersona);
            if(persona!=null){
                guardarUsuario(persona.getCedula(),personaUsuarioRequest.getClave());
                Optional<Usuario> user = usuarioRepository.findByPersona(persona);
                return new PersonaUsuarioResponse(persona.getId(),persona.getCedula(),
                        persona.getApellidos(), persona.getNombres(),persona.getFechaNacimiento(),
                        persona.getEdad(),persona.getGenero(), persona.getTelefono(), persona.getEmail(),user.get().getClave(), generateTokenSignUp(personaUsuarioRequest));
            }else {
                log.error("No se puedo guardar el usuario con cédula: {} e email: {}", personaUsuarioRequest.getCedula(), personaUsuarioRequest.getEmail());
                throw new BadRequestException("No se pudo guardar el usuario");
            }
        }else {
            log.error("La cédula ya está registrada: {}", personaUsuarioRequest.getCedula());
            throw new BadRequestException("La cedula ingresada, ya esta registrada, si la cedula le pertenece contactenos a");
        }
    }


    //Guardar Cliente
    private boolean guardarUsuario(String cedula,String clave){

        Optional<Persona> optionalPersona = personaRepository.findByCedula(cedula);
        if(optionalPersona.isPresent()){
            Persona persona = optionalPersona.get();
            Usuario newUsuario = new Usuario();
            newUsuario.setClave(clave);

            newUsuario.setPersona(persona);
            Usuario user = usuarioRepository.save(newUsuario);
            if(user!=null){
                return true;
            }else{
                throw new BadRequestException("Usuario no registrado");

            }

        }else{
            throw new BadRequestException("La cedula ingresada, no está registrada");

        }
    }

    private boolean getPersona(String cedula) {
        return personaRepository.existsByCedula(cedula);
    }


    //LOGIN

    public PersonaUsuarioResponse login (UsuarioRequest usuarioRequest) throws Exception {
        Optional<Persona> optional = personaRepository.findByEmail(usuarioRequest.getEmail());
        if(optional.isPresent()){
            Optional<Usuario> usuarioOptional= usuarioRepository.findByPersona(optional.get());
            if(usuarioOptional.isPresent()){
                if(usuarioRequest.getClave().equals(usuarioOptional.get().getClave())){
                    return new PersonaUsuarioResponse(optional.get().getId(),optional.get().getCedula(),
                            optional.get().getApellidos(), optional.get().getNombres(),optional.get().getFechaNacimiento(),
                            optional.get().getEdad(),optional.get().getGenero(), optional.get().getTelefono(), optional.get().getEmail(),usuarioOptional.get().getClave(),
                            generateTokenLogin(usuarioRequest));
                }else{
                    throw new BadRequestException("Contraseña incorrecta para email: " + usuarioRequest.getEmail());
                }
            }else{
                log.info("EMAIL NO EXISTE");
                throw new BadRequestException("Usuario no registrado como usuario");
            }
        }else{
            log.info("EMAIL NO EXISTE");
            throw new BadRequestException("Usuario no registrado");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Persona> usuario = personaRepository.findByEmail(email);
        return new org.springframework.security.core.userdetails.User(usuario.get().getEmail(), usuario.get().getEmail(), new ArrayList<>());
    }


    public String generateTokenLogin(UsuarioRequest userRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userRequest.getEmail(), userRequest.getEmail())
            );
        } catch (Exception ex) {
            log.error("INVALID: error al generar token en login de usuario con email: {}", userRequest.getEmail());
            throw new Exception("INAVALID");
        }
        return jwtUtil.generateToken(userRequest.getEmail());
    }

    public String generateTokenSignUp(PersonaUsuarioRequest registerRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(registerRequest.getEmail(), registerRequest.getEmail())
            );
        } catch (Exception ex) {
            log.error("INVALID: error al generar token en signup de usuario con email: {}", registerRequest.getEmail());
            throw new BadRequestException("INAVALID");
        }
        return jwtUtil.generateToken(registerRequest.getEmail());
    }

}
