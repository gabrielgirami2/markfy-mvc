package com.markfy.controller;

import com.markfy.dto.produto.AlterarProdutoDTO;
import com.markfy.dto.usuario.AlterarUsuarioDTO;
import com.markfy.dto.usuario.CadastroUsuarioDTO;
import com.markfy.dto.usuario.UsuarioLoginDTO;
import com.markfy.models.Usuario;
import com.markfy.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    @Transactional
    public String cadastrarApi(@ModelAttribute CadastroUsuarioDTO cadastroUsuarioDTO, Model model, HttpSession session) throws Exception {
        Long idUsuario = (Long) session.getAttribute("usuario");

        if(idUsuario == null){
            return "redirect:/login";
        }

        usuarioService.cadastrar(idUsuario, cadastroUsuarioDTO);
        model.addAttribute("/");
        return "redirect:/loja";
    }

    @PostMapping("/editar/{id}")
    @Transactional
    public String editar(@PathVariable Long id, AlterarUsuarioDTO alterarUsuarioDTO, Model model){
        usuarioService.alterar(id, alterarUsuarioDTO);
        model.addAttribute("/");
        return "redirect:/loja";
    }

    @PostMapping("/deletar/{id}")
    @Transactional
    public String exluir(@PathVariable Long id, Model model){
        usuarioService.deletar(id);
        model.addAttribute("/");
        return "redirect:/loja";
    }


    @PostMapping("/api/cadastrar")
    @Transactional
    public ResponseEntity cadastrarApi(@RequestBody CadastroUsuarioDTO cadastroUsuarioDTO) throws Exception {
        Usuario cadastrar = usuarioService.cadastrarApi(cadastroUsuarioDTO);
        return ResponseEntity.ok().body(cadastrar);
    }

    @GetMapping("/api/listar")
    @Transactional
    public ResponseEntity listarApi() {
        List<Usuario> listar = usuarioService.listar();
        return ResponseEntity.ok().body(listar);
    }


}
