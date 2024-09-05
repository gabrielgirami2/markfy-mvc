package com.markfy.service;

import com.markfy.dto.loja.CadastroLojaDTO;
import com.markfy.models.Loja;
import com.markfy.repository.LojaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LojaService {

    @Autowired
    private LojaRepository lojaRepository;

    public Loja cadastar(CadastroLojaDTO cadastroLojaDTO){
        Loja loja = new Loja(cadastroLojaDTO);
        lojaRepository.save(loja);

        return loja;
    }

    public List<Loja> listar() {
        return lojaRepository.findAll();
    }
}
