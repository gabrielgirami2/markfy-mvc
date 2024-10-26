package com.markfy.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/analise")
public class AnaliseController {

    @GetMapping
    public String paginaAnalise(Model model){
        return "analise/realizar-analise";
    }

}
