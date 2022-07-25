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

}
