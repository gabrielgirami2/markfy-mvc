package com.markfy.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.markfy.dto.cliente.ResponseOllamaDTO;
import com.markfy.dto.ollama.OllamaResponseDTO;
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
        if(usuario == null) return "redirect:/login";

        String nomeUsuario = (String) session.getAttribute("nomeUsuario");
        model.addAttribute("nomeUsuario", nomeUsuario);

        List<Produto> listaProdutos = produtoService.listarProdutosDaLojaDoUsuario(usuario);
        model.addAttribute("produtos", listaProdutos);

        List<Cliente> listaClientes = clienteService.listarClientesDaLojaDoUsuario(usuario);
        model.addAttribute("clientes", listaClientes);

        return "analise/realizar-analise";
    }

    @PostMapping("/produto/realizar")
    public String realizarAnaliseProduto(@RequestParam("produto") Long idProduto, HttpSession session, Model model) {
        Long usuario = (Long) session.getAttribute("usuario");

        String nomeUsuario = (String) session.getAttribute("nomeUsuario");
        model.addAttribute("nomeUsuario", nomeUsuario);

        try {
            List<Produto> listaProdutos = produtoService.listarProdutosDaLojaDoUsuario(usuario);
            model.addAttribute("produtos", listaProdutos);

            List<Cliente> listaClientes = clienteService.listarClientesDaLojaDoUsuario(usuario);
            model.addAttribute("clientes", listaClientes);

            Produto produtoSelecionado = produtoService.buscarPorId(idProduto);
            OllamaResponseDTO resposta = analiseService.analisarProduto(produtoSelecionado, listaClientes);
            model.addAttribute("resposta", resposta);

            return "analise/realizar-analise";
        }catch (Exception e){
            return "analise/realizar-analise";
        }

    }


    @PostMapping("/cliente/realizar")
    public String realizarAnaliseCliente(@RequestParam("cliente") Long idCliente, HttpSession session, Model model) {
        Long usuario = (Long) session.getAttribute("usuario");

        String nomeUsuario = (String) session.getAttribute("nomeUsuario");
        model.addAttribute("nomeUsuario", nomeUsuario);

        try {
            List<Produto> listaProdutos = produtoService.listarProdutosDaLojaDoUsuario(usuario);
            model.addAttribute("produtos", listaProdutos);

            List<Cliente> listaClientes = clienteService.listarClientesDaLojaDoUsuario(usuario);
            model.addAttribute("clientes", listaClientes);

            Cliente clienteSelecionado = clienteService.buscarPorId(idCliente);
            OllamaResponseDTO resposta = analiseService.analisarCliente(clienteSelecionado, listaProdutos);
            model.addAttribute("resposta", resposta);

            return "analise/realizar-analise";
        }catch (Exception e){
            return "analise/realizar-analise";
        }

    }


}
