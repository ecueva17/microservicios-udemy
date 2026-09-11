package com.udemy.springcloud.ms.usuarios.controllers;

import com.udemy.springcloud.ms.usuarios.models.entity.Usuario;
import com.udemy.springcloud.ms.usuarios.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listar(){
        return usuarioService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable(name = "id") Long id){
        Optional<Usuario> usuarioOptional = usuarioService.porId(id);
        if(usuarioOptional.isPresent()){
            return ResponseEntity.ok(usuarioOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario crear(@RequestBody Usuario usuario){
        return usuarioService.guardar(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Usuario usuario,
                                    @PathVariable Long id){
        Optional<Usuario> usuarioOptional = usuarioService.porId(id);
        if(usuarioOptional.isPresent()){
            Usuario usuarioBD = usuarioOptional.get();
            usuarioBD.setNombre(usuario.getNombre());
            usuarioBD.setEmail(usuario.getEmail());
            usuarioBD.setPassword(usuario.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.guardar(usuarioBD));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<Usuario> usuarioOptional = usuarioService.porId(id);
        if(usuarioOptional.isPresent()){
            usuarioService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
