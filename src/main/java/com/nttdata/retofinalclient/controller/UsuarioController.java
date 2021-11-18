/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.retofinalclient.controller;

import com.nttdata.retofinalserver.service.UsuarioService;
import com.nttdata.retofinalserver.usuario.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Manuel
 */
@RestController
public class UsuarioController {

    private final static Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    UsuarioService usuarioService;

    private Counter anadirUsuario;
    private Counter eliminarUsuario;
    private Counter obtenerTodosUsuarios;

    public UsuarioController(MeterRegistry registry) {
        anadirUsuario = Counter.builder("invocaciones.anadir").register(registry);
        eliminarUsuario = Counter.builder("invocaciones.eliminar").register(registry);
    }

    @PostMapping("/anadir")
    public ResponseEntity<Void> anadirUsuario(@RequestBody Usuario usuario) {
        anadirUsuario.increment();
        usuarioService.anadirUsuario(usuario);
        logger.info("Usuario añadido correctamente.");
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/eliminar/{nombre}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable int id) {
        eliminarUsuario.increment();
        usuarioService.eliminarUsuario(id);
        logger.info("Usuario añadido correctamente.");
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    
    @GetMapping()
    public ResponseEntity<List<Usuario>> obtenerTodosUsuarios(){
        obtenerTodosUsuarios.increment();
        logger.info("Usuario añadido correctamente.");
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.obtenerTodosUsuarios());
    }
}
