package com.markfy.controller;

import com.markfy.dto.usuario.UsuarioLoginDTO;
import com.markfy.models.Produto;
import com.markfy.service.ProdutoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ProdutoService produtoService;


    @GetMapping
    public String inicio(Model model){
        List<Produto> produtos = produtoService.listar();
        model.addAttribute("produtos", produtos);
        return "inicial";
    }

    @GetMapping("/home")
    public String home(Model model){
        return "home";
    }

    @GetMapping("/login")
    public String login(UsuarioLoginDTO usuarioLoginDTO, Model model){
        return "login";
    }

    @GetMapping("/contato")
    public String contato(UsuarioLoginDTO usuarioLoginDTO, Model model){
        return "contato";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String onError(){
        return "redirect:/";
    }
}
