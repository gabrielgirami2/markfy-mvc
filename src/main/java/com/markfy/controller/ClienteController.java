package com.markfy.controller;

import com.markfy.dto.produto.AlterarProdutoDTO;
import com.markfy.dto.produto.CadastroProdutoDTO;
import com.markfy.models.Cliente;
import com.markfy.models.Produto;
import com.markfy.service.ClienteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String paginaCliente(Model model, HttpSession session, CadastroProdutoDTO cadastroProdutoDTO, AlterarProdutoDTO alterarProdutoDTO){
        Long idUsuario = (Long) session.getAttribute("usuario");

        if(idUsuario == null){return "redirect:/login";}

        List<Produto> produtos = clienteService.listarProdutosDaLojaDoUsuario(idUsuario);
        model.addAttribute("produtos", produtos);
        return "produto/lista-produtos";
    }
}
