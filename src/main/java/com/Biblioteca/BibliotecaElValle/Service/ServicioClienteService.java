package com.Biblioteca.BibliotecaElValle.Service;


import com.Biblioteca.BibliotecaElValle.Dao.Estadisticas.*;
import com.Biblioteca.BibliotecaElValle.Dao.ServicioCliente.ServicioClienteListResponse;
import com.Biblioteca.BibliotecaElValle.Dao.ServicioCliente.ServicioClienteRequest;
import com.Biblioteca.BibliotecaElValle.Excepciones.BadRequestException;
import com.Biblioteca.BibliotecaElValle.Models.Persona.Cliente;
import com.Biblioteca.BibliotecaElValle.Models.Servicio.Servicio;
import com.Biblioteca.BibliotecaElValle.Models.Servicio.ServicioCliente;
import com.Biblioteca.BibliotecaElValle.Repository.Curso.CursoRepository;
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

    @Autowired
    private CursoRepository cursoRepository;

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



                Long numInfantes= personaRepository.countByEdadAndEdadAndMesAndAnio((long)0,(long)5,mes, anio);
                Long numNinos= personaRepository.countByEdadAndEdadAndMesAndAnio((long)6,(long)11,mes, anio);
                Long numAdolescentes= personaRepository.countByEdadAndEdadAndMesAndAnio((long)12,(long)17,mes, anio);
                Long numJovenes= personaRepository.countByEdadAndEdadAndMesAndAnio((long)18,(long)25,mes, anio);
                Long numAdultos= personaRepository.countByEdadAndEdadAndMesAndAnio((long)26,(long)64,mes, anio);
                Long numAdultosMayores= personaRepository.countByEdadAndEdadAndMesAndAnio((long)65,(long)150,mes, anio);


                //DE ACUERDO A LOS CURSOS
                Long numInfantesCurso= cursoRepository.countDistinctByEdadAndEdadAndMesInicioAndAnioInicio((long)0,(long)5,mes, anio);
                Long numNinosCurso= cursoRepository.countDistinctByEdadAndEdadAndMesInicioAndAnioInicio((long)6,(long)11,mes, anio);
                Long numAdolescentesCurso= cursoRepository.countDistinctByEdadAndEdadAndMesInicioAndAnioInicio((long)12,(long)17,mes, anio);
                Long numJovenesCurso= cursoRepository.countDistinctByEdadAndEdadAndMesInicioAndAnioInicio((long)18,(long)25,mes, anio);
                Long numAdultosCurso= cursoRepository.countDistinctByEdadAndEdadAndMesInicioAndAnioInicio((long)26,(long)64,mes, anio);
                Long numAdultosMayoresCurso= cursoRepository.countDistinctByEdadAndEdadAndMesInicioAndAnioInicio((long)64,(long)150,mes, anio);


                Long numInfantesGeneral= numInfantes+numInfantesCurso;
                Long numNinosGeneral= numNinos+numNinosCurso;
                Long numAdolescentesGeneral= numAdolescentes+numAdolescentesCurso;
                Long numJovenesGeneral= numJovenes+numJovenesCurso;
                Long numAdultosGeneral= numAdultos+numAdultosCurso;
                Long numAdultosMayoresGeneral= numAdultosMayores+numAdultosMayoresCurso;

                Long total= numInfantesGeneral+numNinosGeneral+numAdolescentesGeneral+numJovenesGeneral+numAdultosGeneral+numAdultosMayoresGeneral;

                Datos datosInfantes = new Datos();
                datosInfantes.setNum(numInfantesGeneral);
                datosInfantes.setPct(calcularPorcentaje(total, numInfantesGeneral));

                Datos datosNinos = new Datos();
                datosNinos.setNum(numNinosGeneral);
                datosNinos.setPct(calcularPorcentaje(total,numNinosGeneral));


                Datos datosAdolescentes = new Datos();
                datosAdolescentes.setNum(numAdolescentesGeneral);
                datosAdolescentes.setPct(calcularPorcentaje(total,numAdolescentesGeneral));

                Datos datosJovenes = new Datos();
                datosJovenes.setNum(numJovenesGeneral);
                datosJovenes.setPct(calcularPorcentaje(total,numJovenesGeneral));

                Datos datosAdultos = new Datos();
                datosAdultos.setNum(numAdultosGeneral);
                datosAdultos.setPct(calcularPorcentaje(total,numAdultosGeneral));

                Datos datosAdultosMayores = new Datos();
                datosAdultosMayores.setNum(numAdultosMayoresGeneral);
                datosAdultosMayores.setPct(calcularPorcentaje(total,numAdultosMayoresGeneral));



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


   @Transactional
    public FiltrarServiciosResponse filtrarServicios(Long mes, Long anio){



        Long numRepos = servicioClienteRepository.countByMesAndAnioAndNombreLikeIgnoreCase(mes, anio, "repositorio");
        Long numBiblioteca = servicioClienteRepository.countByMesAndAnioAndNombreLikeIgnoreCase(mes, anio, "biblioteca");
        Long numInternet = servicioClienteRepository.countByMesAndAnioAndNombreLikeIgnoreCase(mes, anio, "internet");
        Long numImpre = servicioClienteRepository.countByMesAndAnioAndNombreLikeIgnoreCase(mes, anio, "impresora");
        Long numCurso = cursoRepository.countDistinctByMesInicioAndAnioInicio(mes, anio);

       Long total = numRepos+numBiblioteca+numInternet+numImpre+numCurso;



        Datos datosRepos= new Datos();
        datosRepos.setNum(numRepos);
        datosRepos.setPct(calcularPorcentaje(total,numRepos));

        Datos datosBibliot = new Datos();
        datosBibliot.setNum(numBiblioteca);
        datosBibliot.setPct(calcularPorcentaje(total,numBiblioteca));

        Datos datosInternet = new Datos();
        datosInternet.setNum(numInternet);
        datosInternet.setPct(calcularPorcentaje(total,numInternet));

        Datos datosImpre = new Datos();
        datosImpre.setNum(numImpre);
        datosImpre.setPct(calcularPorcentaje(total,numImpre));

        Datos datosCurso = new Datos();
        datosCurso.setNum(numCurso);
        datosCurso.setPct(calcularPorcentaje(total,numCurso));


        FiltrarServiciosResponse response = new FiltrarServiciosResponse();
        response.setMes(mes);
        response.setAnio(anio);
        response.setTotal(total);
        response.setRepositorio(datosRepos);
        response.setBiblioteca(datosBibliot);
        response.setInternet(datosInternet);
        response.setImprecopias(datosImpre);
        response.setTallactv(datosCurso);

        return response;
    }


    @Transactional
    public FiltrarDiscapacidadResponse filtrarByDiscapacidad(Long mes, Long anio){
        Long totalS = servicioClienteRepository.countByMesAndAnio(mes, anio);
        Long numCurso = cursoRepository.countDistinctByMesInicioAndAnioInicio(mes, anio);

        Long total = totalS+numCurso;

        Long numDiscacidadSi= servicioClienteRepository.countByMesAndAnioAndDiscapacidad(mes, anio, true);
        Long numDiscacidadNo= servicioClienteRepository.countByMesAndAnioAndDiscapacidad(mes, anio, false);
        Long numDiscacidadSiCurso = cursoRepository.countDistinctByMesAndAnioAndDiscapacidad(mes, anio, true);
        Long numDiscacidadNoCurso= cursoRepository.countDistinctByMesAndAnioAndDiscapacidad(mes, anio, false);

        Long numDiscacidadSiGeneral= numDiscacidadSi+numDiscacidadSiCurso;
        Long numDiscacidadNoGeneral= numDiscacidadNo+numDiscacidadNoCurso;

        Datos datosSi = new Datos();
        datosSi.setNum(numDiscacidadSiGeneral);
        datosSi.setPct(calcularPorcentaje(total,numDiscacidadSiGeneral));

        Datos datosNo= new Datos();
        datosNo.setNum(numDiscacidadNoGeneral);
        datosNo.setPct(calcularPorcentaje(total,numDiscacidadNoGeneral));

        FiltrarDiscapacidadResponse response = new FiltrarDiscapacidadResponse();
        response.setMes(mes);
        response.setAnio(anio);
        response.setTotal(total);
        response.setNo(datosNo);
        response.setSi(datosSi);

        return response;
    }



    @Transactional
    public FiltrarGeneroResponse filtrarByGenero(Long mes, Long anio){
        Long totalS = servicioClienteRepository.countByMesAndAnio(mes, anio);
        Long numCurso = cursoRepository.countDistinctByMesInicioAndAnioInicio(mes, anio);
        Long total= totalS+numCurso;

        //SERVICIOS Y CLIENTE
        Long numMasculino = personaRepository.countByGeneroAndMesAndAnio("masculino",mes,anio);
        Long numFemenino = personaRepository.countByGeneroAndMesAndAnio("femenino",mes,anio);
        Long numOtros = personaRepository.countByGeneroAndMesAndAnio("otros",mes,anio);

        //CUROS

        Long numMasculinoCurso= cursoRepository.countDistinctByGeneroAndMesInicioAndAnioInicio("masculino",mes,anio);
        Long numFemeninoCurso = cursoRepository.countDistinctByGeneroAndMesInicioAndAnioInicio("femenino",mes,anio);
        Long numOtrosCurso = cursoRepository.countDistinctByGeneroAndMesInicioAndAnioInicio("otros",mes,anio);

        Long numMasculinoGeneral=  numMasculino+numMasculinoCurso;
        Long numFemeninoGeneral= numFemenino+numFemeninoCurso;
        Long numOtrosGeneral= numOtros+numOtrosCurso;

        Datos datosMas= new Datos();
        datosMas.setNum(numMasculinoGeneral);
        datosMas.setPct(calcularPorcentaje(total,numMasculinoGeneral));

        Datos datosFem= new Datos();
        datosFem.setNum(numFemeninoGeneral);
        datosFem.setPct(calcularPorcentaje(total,numFemeninoGeneral));

        Datos datosOtros= new Datos();
        datosOtros.setNum(numOtrosGeneral);
        datosOtros.setPct(calcularPorcentaje(total,numOtrosGeneral));

        FiltrarGeneroResponse response = new FiltrarGeneroResponse();
        response.setMes(mes);
        response.setAnio(anio);
        response.setTotal(total);
        response.setMasculino(datosMas);
        response.setFemenino(datosFem);
        response.setOtros(datosOtros);

        return response;
    }



}
