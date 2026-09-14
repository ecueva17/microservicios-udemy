package com.udemy.springcloud.ms.cursos.controllers;

import com.udemy.springcloud.ms.cursos.models.entity.Curso;
import com.udemy.springcloud.ms.cursos.services.CursoService;
import jakarta.validation.Valid;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<Curso>> listar(){
        return ResponseEntity.ok(cursoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable(name = "id") Long id){
        Optional<Curso> cursoOptional = cursoService.porId(id);
        if(cursoOptional.isPresent()){
            return ResponseEntity.ok(cursoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> crear(@Valid @RequestBody Curso curso, BindingResult result){
        if(result.hasErrors()){
            return validar(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.guardar(curso));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Curso curso, BindingResult result,
                                    @PathVariable Long id){
        if(result.hasErrors()){
            return validar(result);
        }

        Optional<Curso> cursoOptional = cursoService.porId(id);
        if(cursoOptional.isPresent()){
            Curso cursoBD = cursoOptional.get();
            cursoBD.setNombre(curso.getNombre());
            return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.guardar(cursoBD));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<Curso> cursoOptional = cursoService.porId(id);
        if(cursoOptional.isPresent()){
            cursoService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private static @NonNull ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
