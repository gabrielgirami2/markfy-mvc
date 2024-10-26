package com.markfy.controller;

import com.markfy.dto.usuario.UsuarioLoginDTO;
import com.markfy.models.Cliente;
import com.markfy.models.Produto;
import com.markfy.models.enums.*;
import com.markfy.service.ProdutoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private OpenAiChatModel aiChatModel;

    @GetMapping("/markfy/estrategias")
    public String estrategiasMarkfyIA(){
        ArrayList<Cliente> clientes =  new ArrayList<>();

        Cliente cliente1 = new Cliente(
                "Ana",
                "Silva",
                "ana.silva@email.com",
                LocalDate.of(1990, 5, 15), // Data de nascimento
                SexoEnum.FEMININO,
                "123.456.789-00",
                EstadoCivilEnum.CASADO,
                NivelEducacionalEnum.SUPERIOR_GRADUACAO,
                50000.00f,
                OcupacaoEnum.EMPREGADO
        );

        Cliente cliente2 = new Cliente(
                "Carlos",
                "Oliveira",
                "carlos.oliveira@email.com",
                LocalDate.of(1985, 8, 30),
                SexoEnum.MASCULINO,
                "987.654.321-00",
                EstadoCivilEnum.SOLTEIRO,
                NivelEducacionalEnum.POS_GRADUACAO,
                75000.00f,
                OcupacaoEnum.EMPREGADO
        );

        clientes.add(cliente1);
        clientes.add(cliente2);

        ArrayList<Produto> produtos = new ArrayList<>();

        Produto produto1 = new Produto(
                "Camiseta",
                39.99f,
                "Nike",
                TamanhoEnum.M,
                100
        );

        Produto produto2 = new Produto(
                "Calça Jeans",
                89.90f,
                "Levi's",
                TamanhoEnum.GG,
                50
        );


        return aiChatModel.call("Me forneça 5 as melhores estratégias de marketing para minha loja de roupas, considerando que tenhos esses clientes: "
                        + clientes
                        + " e esses produtos em estoque: "
                        + produtos
        );
    }

    @GetMapping
    public String inicio(Model model){
        List<Produto> produtos = produtoService.listar();
        model.addAttribute("produtos", produtos);
        return "inicial";
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model){
        String nomeUsuario = (String) session.getAttribute("nomeUsuario");
        model.addAttribute("nomeUsuario", nomeUsuario);
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
