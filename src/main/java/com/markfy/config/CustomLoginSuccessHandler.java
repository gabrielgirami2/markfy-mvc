package com.markfy.config;

import com.markfy.models.Usuario;
import com.markfy.repository.UsuarioRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.io.IOException;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Usuario usuario = usuarioRepository.findByEmailUsuario(authentication.getName());
        session.setAttribute("usuario", usuario.getIdUsuario());
        session.setAttribute("nomeUsuario", usuario.getNomeUsuario());

        response.sendRedirect("/home");
    }
}

