package com.markfy.service;

import com.markfy.dto.loja.CadastroLojaDTO;
import com.markfy.dto.loja.CadastroLojaUsuarioDTO;
import com.markfy.dto.usuario.CadastroUsuarioDTO;
import com.markfy.models.Loja;
import com.markfy.models.Usuario;
import com.markfy.repository.LojaRepository;
import com.markfy.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LojaService {

    @Autowired
    private LojaRepository lojaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Loja cadastar(CadastroLojaDTO cadastroLojaDTO){
        Loja loja = new Loja(cadastroLojaDTO);
        lojaRepository.save(loja);

        return loja;
    }

    public Long cadastarLojaUsuario(CadastroLojaUsuarioDTO cadastroLojaUsuarioDTO){
        CadastroLojaDTO cadastroLojaDTO = new CadastroLojaDTO(cadastroLojaUsuarioDTO.nomeLoja(), cadastroLojaUsuarioDTO.cnpj());
        Loja loja = new Loja(cadastroLojaDTO);
        Loja lojaSave = lojaRepository.save(loja);

        Usuario usuario = new Usuario(cadastroLojaUsuarioDTO.nomeUsuario(),
                        cadastroLojaUsuarioDTO.sobrenomeUsuario(),
                        cadastroLojaUsuarioDTO.emailUsuario(),
                        cadastroLojaUsuarioDTO.senha(),
                        lojaSave);

        Usuario usuarioSave = usuarioRepository.save(usuario);
        System.out.println(lojaSave);
        System.out.println(usuarioSave);
        return usuarioSave.getIdUsuario();
    }

    public List<Loja> listar() {
        return lojaRepository.findAll();
    }
}
