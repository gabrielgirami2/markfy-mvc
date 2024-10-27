package com.markfy.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.markfy.dto.cliente.ResponseOllamaDTO;
import com.markfy.models.Cliente;
import com.markfy.models.Produto;
import com.markfy.service.AnaliseService;
import com.markfy.service.ClienteService;
import com.markfy.service.ProdutoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/analise")
public class AnaliseController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private AnaliseService analiseService;

    @GetMapping
    public String paginaAnalise(Model model, HttpSession session){
        Long usuario = (Long) session.getAttribute("usuario");
        String nomeUsuario = (String) session.getAttribute("nomeUsuario");
        model.addAttribute("nomeUsuario", nomeUsuario);

        if(usuario == null) return "redirect:/login";

        List<Produto> listaProdutos = produtoService.listarProdutosDaLojaDoUsuario(usuario);

        model.addAttribute("produtos", listaProdutos);

        return "analise/realizar-analise";
    }

    @PostMapping("/realizar")
    public String realizarAnalise(@RequestParam("produto") Long idProduto, HttpSession session, Model model) throws JsonProcessingException {
        Long usuario = (Long) session.getAttribute("usuario");
        String nomeUsuario = (String) session.getAttribute("nomeUsuario");
        model.addAttribute("nomeUsuario", nomeUsuario);

        List<Produto> listaProdutos = produtoService.listar();
        model.addAttribute("produtos", listaProdutos);

        Produto produtoSelecionado = produtoService.buscarPorId(idProduto);
        ResponseOllamaDTO clientesAnalise = analiseService.analisar(produtoSelecionado);

        model.addAttribute("cliente", clientesAnalise);

        return "analise/realizar-analise";
    }


}
