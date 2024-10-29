package com.markfy.config;


import com.markfy.models.Loja;
import com.markfy.models.Usuario;
import com.markfy.repository.LojaRepository;
import com.markfy.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.HashSet;

@Configuration
public class DataInitializer {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private LojaRepository lojaRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostConstruct
    public void init() {
        if (usuarioRepository.findByEmailUsuario("admin@admin.com") == null
                && usuarioRepository.findByEmailUsuario("user@email.com") == null) {
            Loja loja = new Loja("Loja exemplo", "12.015.104/0001-59");
            Loja lojaSave = lojaRepository.save(loja);

            Usuario admin = new Usuario();
            admin.setNomeUsuario("Admin");
            admin.setSobrenomeUsuario("User");
            admin.setEmailUsuario("admin@admin.com");
            admin.setSenha(passwordEncoder.encode("admin123"));
            admin.setRoles(new HashSet<>(Collections.singletonList("ROLE_ADMIN")));
            admin.setLoja(lojaSave);
            usuarioRepository.save(admin);

            Usuario user = new Usuario();
            user.setNomeUsuario("User");
            user.setSobrenomeUsuario("Unnamed");
            user.setEmailUsuario("user@email.com");
            user.setSenha(passwordEncoder.encode("user123"));
            user.setRoles(new HashSet<>(Collections.singletonList("ROLE_USER")));
            user.setLoja(lojaSave);
            usuarioRepository.save(user);
        }
    }
}
