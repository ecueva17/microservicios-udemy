package com.udemy.springcloud.ms.cursos.repositories;

import com.udemy.springcloud.ms.cursos.models.entity.Curso;
import org.springframework.data.repository.CrudRepository;

public interface CursoRepository extends CrudRepository<Curso, Long> {
}
