package com.markfy.service;

import com.markfy.dto.loja.AlterarLojaDTO;
import com.markfy.dto.loja.CadastroLojaDTO;
import com.markfy.dto.loja.CadastroLojaUsuarioDTO;
import com.markfy.models.Loja;
import com.markfy.models.Usuario;
import com.markfy.repository.LojaRepository;
import com.markfy.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service
public class LojaService {

    @Autowired
    private LojaRepository lojaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public Loja cadastar(CadastroLojaDTO cadastroLojaDTO){
        Loja loja = new Loja(cadastroLojaDTO);
        lojaRepository.save(loja);

        return loja;
    }

    public Long cadastarLojaUsuario(CadastroLojaUsuarioDTO cadastroLojaUsuarioDTO) throws Exception {
        CadastroLojaDTO cadastroLojaDTO = new CadastroLojaDTO(cadastroLojaUsuarioDTO.nomeLoja(), cadastroLojaUsuarioDTO.cnpj());
        Loja loja = new Loja(cadastroLojaDTO);
        Loja lojaSave = lojaRepository.save(loja);


        Usuario usuario = new Usuario(cadastroLojaUsuarioDTO.nomeUsuario(),
                        cadastroLojaUsuarioDTO.sobrenomeUsuario(),
                        cadastroLojaUsuarioDTO.emailUsuario(),
                        cadastroLojaUsuarioDTO.senha(),
                        lojaSave);

        if (usuarioRepository.findByEmailUsuario(usuario.getEmailUsuario()) != null) {
            throw new Exception("Esse usuário já possui um cadastro");
        }

        String senhaEncode = passwordEncoder.encode(usuario.getSenha());
        HashSet<String> roleUser = new HashSet<>(Collections.singletonList("ROLE_ADMIN"));

        Usuario admin = new Usuario(usuario.getNomeUsuario(),
                usuario.getSobrenomeUsuario(),
                usuario.getEmailUsuario(),
                senhaEncode,
                roleUser,
                usuario.getLoja()
        );

        Usuario usuarioSave = usuarioRepository.save(admin);

        return usuarioSave.getIdUsuario();
    }

    public List<Loja> listar() {
        return lojaRepository.findAll();
    }

    public Loja buscarLojaPorIdUsuario(Long usuario) {
        Usuario usuarioById = usuarioRepository.findById(usuario).get();
        Loja loja = lojaRepository.getReferenceById(usuarioById.getLoja().getIdLoja());
        return loja;
    }

    public Loja alterarLoja(AlterarLojaDTO alterarLojaDTO, Long id) {
        Loja lojaById = lojaRepository.findById(id).get();

        lojaById.alterar(alterarLojaDTO);
        lojaRepository.save(lojaById);
        return lojaById;
    }
}
