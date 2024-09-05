package com.markfy.controller;

import com.markfy.dto.usuario.CadastroUsuarioDTO;
import com.markfy.dto.usuario.UsuarioLoginDTO;
import com.markfy.models.Usuario;
import com.markfy.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/entrar")
    @Transactional
    public String entrar(UsuarioLoginDTO usuarioLoginDTO, Model model, HttpSession session){
        try {
            Usuario usuario = usuarioService.entrar(usuarioLoginDTO);
            session.setAttribute("usuario", usuario.getIdUsuario());
            model.addAttribute("/");
            return "redirect:/home";
        } catch (Exception e) {
            model.addAttribute("/");
            return "redirect:/login";
        }
    }

    @PostMapping("/api/cadastrar")
    @Transactional
    public ResponseEntity cadastrarApi(@RequestBody CadastroUsuarioDTO cadastroUsuarioDTO) throws Exception {
        Usuario cadastrar = usuarioService.cadastrar(cadastroUsuarioDTO);
        return ResponseEntity.ok().body(cadastrar);
    }

    @GetMapping("/api/listar")
    @Transactional
    public ResponseEntity listarApi() {
        List<Usuario> listar = usuarioService.listar();
        return ResponseEntity.ok().body(listar);
    }


}
