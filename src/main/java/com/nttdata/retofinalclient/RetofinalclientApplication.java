package com.nttdata.retofinalclient;

import com.nttdata.retofinalserver.service.UsuarioService;
import com.nttdata.retofinalserver.usuario.UsuarioBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RetofinalclientApplication implements CommandLineRunner{

    @Autowired
    UsuarioBuilder usuarioBuilder;
    
    @Autowired
    UsuarioService usuarioService;
    
    public static void main(String[] args) {
        SpringApplication.run(RetofinalclientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        usuarioService.getListaUsuarios().add(usuarioBuilder.id(1).nombre("Manuel").build());
        usuarioService.getListaUsuarios().add(usuarioBuilder.id(2).nombre("Paco").build());
    }

}
