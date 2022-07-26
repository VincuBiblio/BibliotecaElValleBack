package com.Biblioteca.BibliotecaElValle.Repository.Persona;

import com.Biblioteca.BibliotecaElValle.Models.Persona.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona,Long> {

    Boolean existsByCedula (String cedula);

    Optional<Persona> findByCedula(String cedula);

    Optional<Persona> findByEmail(String email);

    @Query(value = "select  count(*)\n" +
            "from persona p, cliente cl, servicio s, servicio_cliente sc \n" +
            "where p.edad between  :edad and :edad2 and sc.mes = :mes and sc.anio = :anio and cl.persona_id = p.id and sc.id_servicio = s.id and sc.id_cliente = cl.id",nativeQuery = true)
    Long countByEdadAndEdadAndMesAndAnio(Long edad, Long edad2, Long mes, Long anio);


    @Query(value = "select  count(*)\n" +
            "from persona p, cliente cl, servicio s, servicio_cliente sc \n" +
            "where p.genero = :genero and sc.mes  = :mes and sc.anio  = :anio and cl.persona_id = p.id and sc.id_servicio = s.id and sc.id_cliente = cl.id",nativeQuery = true)

    Long countByGeneroAndMesAndAnio(String genero, Long mes, Long anio);


}
