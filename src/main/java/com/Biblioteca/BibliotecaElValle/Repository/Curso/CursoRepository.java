package com.Biblioteca.BibliotecaElValle.Repository.Curso;

import com.Biblioteca.BibliotecaElValle.Models.Cursos.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso,Long> {


    @Query(value = "SELECT DISTINCT c.nombre as curso, p.genero as genero\n" +
            "FROM curso c, cliente cl, persona p, cursos_clientes cc \n" +
            "where c.mes_inicio = :mes and c.anio_inicio = :anio and c.id = cc.curso_id and cl.id = cc.cliente_id and cl.persona_id = p.id\n", nativeQuery = true)
    List<CursoClienteConsultaResponse> findDistinctByMesInicioAndAnioInicio(Long mes, Long anio);
    List<Curso> findByMesInicioAndAnioInicio(Long mes, Long anio);

    @Query(value = "select distinct  count(*)\n" +
            "from curso c, cursos_clientes cc, cliente cl\n" +
            "where c.mes_inicio = :mes and c.anio_inicio = :anio and cc.cliente_id = cl.id and cc.curso_id = c.id ",nativeQuery = true)
    Long countDistinctByMesInicioAndAnioInicio(Long mes, Long anio);


    @Query(value = "select distinct count(*)\n" +
            "from persona p, cliente cl, curso c, cursos_clientes cc\n" +
            "where p.edad between  :edad and :edad2 and c.mes_inicio = :mes and c.anio_inicio = :anio and cl.persona_id = p.id and cc.cliente_id = cl.id and cc.curso_id = c.id", nativeQuery = true)
    Long countDistinctByEdadAndEdadAndMesInicioAndAnioInicio(Long edad, Long edad2, Long mes, Long anio);


    @Query(value = "select distinct  count(*) \n" +
            "from  cliente cl,  curso c, cursos_clientes cc\n" +
            "where cl.discapacidad = :discapacidad and c.mes_inicio = :mes and c.anio_inicio = :anio and cc.cliente_id = cl.id and cc.curso_id = c.id", nativeQuery = true)
    Long countDistinctByMesAndAnioAndDiscapacidad(Long mes, Long anio, Boolean discapacidad);


    @Query(value = "select  distinct count(*)\n" +
            "from persona p, cliente cl,  curso c, cursos_clientes cc\n" +
            "where p.genero = :genero and c.mes_inicio = :mes and c.anio_inicio = :anio and cl.persona_id = p.id and cc.cliente_id = cl.id and cc.curso_id = c.id", nativeQuery = true)
    Long countDistinctByGeneroAndMesInicioAndAnioInicio(String genero,Long mes, Long anio);
}
