package com.markfy.controller;

import com.markfy.dto.cliente.CadastroClienteDTO;
import com.markfy.dto.loja.CadastroLojaDTO;
import com.markfy.dto.loja.CadastroLojaUsuarioDTO;
import com.markfy.dto.usuario.CadastroUsuarioDTO;
import com.markfy.dto.usuario.UsuarioLoginDTO;
import com.markfy.models.Loja;
import com.markfy.service.LojaService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/loja")
public class LojaController {

    @Autowired
    private LojaService lojaService;


    @GetMapping("/cadastro")
    public String formularioCadastro(CadastroLojaUsuarioDTO cadastroLojaUsuarioDTO, Model model){
        return "loja/cadastro-loja";
    }

    @PostMapping("/cadastrar")
    @Transactional
    public String cadastrarLoja(@ModelAttribute CadastroLojaUsuarioDTO cadastroLojaUsuarioDTO, HttpSession session, BindingResult result, Model model){
        Long usuarioId = lojaService.cadastarLojaUsuario(cadastroLojaUsuarioDTO);
        session.setAttribute("usuario", usuarioId);
        model.addAttribute("/");
        return "redirect:/home";
    }

    @PostMapping("/api/cadastrar")
    @Transactional
    public ResponseEntity<Loja> cadastrarLojaApi(@RequestBody CadastroLojaDTO cadastroLojaDTO){
        Loja cadastar = lojaService.cadastar(cadastroLojaDTO);
        return ResponseEntity.ok(cadastar);
    }

    @GetMapping("/api/listar")
    public ResponseEntity<List<Loja>> listarApi(){
        List<Loja> listar = lojaService.listar();
        return ResponseEntity.ok(listar);
    }


}
