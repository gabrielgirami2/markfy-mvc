package com.markfy.controller;

import com.markfy.dto.produto.AlterarProdutoDTO;
import com.markfy.dto.produto.CadastroProdutoDTO;
import com.markfy.models.Produto;
import com.markfy.service.ProdutoService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.List;


@Controller
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;


    @GetMapping
    public String paginaProduto(Model model, HttpSession session, CadastroProdutoDTO cadastroProdutoDTO, AlterarProdutoDTO alterarProdutoDTO){
        Long idUsuario = (Long) session.getAttribute("usuario");

        if(idUsuario == null){return "redirect:/login";}

        List<Produto> produtos = produtoService.listarProdutosDaLojaDoUsuario(idUsuario);
        model.addAttribute("produtos", produtos);
        return "produto/lista-produtos";
    }

    @PostMapping("/cadastrar")
    @Transactional
    public String novo(@Valid CadastroProdutoDTO cadastroProdutoDTO, HttpSession session, Model model){
        Long idUsuario = (Long) session.getAttribute("usuario");

        if(idUsuario == null){
            return "redirect:/login";
        }

        produtoService.cadastrar(idUsuario, cadastroProdutoDTO);
        model.addAttribute("/");
        return "redirect:/produto";
    }

    @PostMapping("/editar/{id}")
    @Transactional
    public String editar(@PathVariable Long id, AlterarProdutoDTO alterarProdutoDTO, Model model){
        produtoService.alterar(id, alterarProdutoDTO);
        model.addAttribute("/");
        return "redirect:/produto";
    }

    @PostMapping("/deletar/{id}")
    @Transactional
    public String deletar(@PathVariable Long id, Model model){
        produtoService.deletar(id);
        model.addAttribute("/");
        return "redirect:/produto";
    }


}
