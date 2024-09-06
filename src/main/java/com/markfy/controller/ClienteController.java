package com.markfy.controller;

import com.markfy.dto.cliente.AlterarClienteDTO;
import com.markfy.dto.cliente.CadastroClienteDTO;
import com.markfy.dto.produto.AlterarProdutoDTO;
import com.markfy.dto.produto.CadastroProdutoDTO;
import com.markfy.models.Cliente;
import com.markfy.models.Produto;
import com.markfy.service.ClienteService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String paginaCliente(Model model, HttpSession session, CadastroClienteDTO cadastroClienteDTO, AlterarClienteDTO alterarClienteDTO){
        Long idUsuario = (Long) session.getAttribute("usuario");

        if(idUsuario == null){return "redirect:/login";}

        List<Cliente> clientes = clienteService.listarClientesDaLojaDoUsuario(idUsuario);
        model.addAttribute("clientes", clientes);
        return "cliente/tabela-clientes";
    }

    @PostMapping("/cadastrar")
    @Transactional
    public String novo(@Valid CadastroClienteDTO cadastroClienteDTO, HttpSession session, BindingResult result, Model model){
        Long idUsuario = (Long) session.getAttribute("usuario");

        if(idUsuario == null){
            return "redirect:/login";
        }

        try {
            clienteService.cadastrar(idUsuario, cadastroClienteDTO);
            model.addAttribute("/");
            return "redirect:/cliente";
        }catch (Exception e){
            return "redirect:/login";

        }
    }


    @PostMapping("/editar/{id}")
    @Transactional
    public String editar(@PathVariable Long id, AlterarClienteDTO alterarClienteDTO, Model model){
        clienteService.alterar(id, alterarClienteDTO);
        model.addAttribute("/");
        return "redirect:/cliente";
    }

    @PostMapping("/deletar/{id}")
    @Transactional
    public String deletar(@PathVariable Long id, Model model){
        clienteService.deletar(id);
        model.addAttribute("/");
        return "redirect:/cliente";
    }

}
