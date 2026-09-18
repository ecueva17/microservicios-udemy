package com.udemy.springcloud.ms.usuarios.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-cursos", url = "app_ms_cursos:8002")
public interface CursoClienteRest {

    @DeleteMapping("/eliminar-curso-usuario/{id}")
    void eliminarCursoUsuarioPorId(@PathVariable Long id);
}
