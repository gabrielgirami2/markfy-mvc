package com.markfy.controller;

import com.markfy.dto.loja.CadastroLojaDTO;
import com.markfy.models.Loja;
import com.markfy.service.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/loja")
public class LojaController {

    @Autowired
    private LojaService lojaService;

    @PostMapping("/api/cadastrar")
    public ResponseEntity<Loja> cadastrarLoja(@RequestBody CadastroLojaDTO cadastroLojaDTO){
        Loja cadastar = lojaService.cadastar(cadastroLojaDTO);
        return ResponseEntity.ok(cadastar);
    }

    @GetMapping("/api/listar")
    public ResponseEntity<List<Loja>> listarApi(){
        List<Loja> listar = lojaService.listar();
        return ResponseEntity.ok(listar);
    }


}
