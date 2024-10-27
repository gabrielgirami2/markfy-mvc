package com.markfy.service;

import com.markfy.dto.usuario.AlterarUsuarioDTO;
import com.markfy.dto.usuario.CadastroUsuarioDTO;
import com.markfy.dto.usuario.UsuarioLoginDTO;
import com.markfy.models.Loja;
import com.markfy.models.Produto;
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
import java.util.Optional;

@Service
public class UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private LojaRepository lojaRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public Usuario cadastrarApi(CadastroUsuarioDTO cadastroUsuarioDTO) throws Exception {
        Usuario usuarioByEmail = usuarioRepository.findByEmailUsuario(cadastroUsuarioDTO.emailUsuario());

        if(usuarioByEmail != null){
            throw new Exception("O usuário informado já possui um cadasto.");
        }

        Loja lojaById = lojaRepository.getReferenceById(cadastroUsuarioDTO.idLoja());

        Usuario usuario = new Usuario(cadastroUsuarioDTO, lojaById);
        usuarioRepository.save(usuario);
        return usuario;
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario alterar(Long id, AlterarUsuarioDTO alterarUsuarioDTO) {
        Usuario usuarioById = usuarioRepository.findById(id).get();
        usuarioById.alterarUsuario(alterarUsuarioDTO);
        usuarioRepository.save(usuarioById);
        return usuarioById;
    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }


    public Usuario cadastrar(Long idUsuario, CadastroUsuarioDTO cadastroUsuarioDTO) throws Exception {
        Loja loja = usuarioRepository.findById(idUsuario).get().getLoja();

        if (usuarioRepository.findByEmailUsuario(cadastroUsuarioDTO.emailUsuario()) != null) {
            throw new Exception("Esse usuário já possui um cadastro");
        }

        String senhaEncode = passwordEncoder.encode(cadastroUsuarioDTO.senha());
        HashSet<String> roleUser = new HashSet<>(Collections.singletonList("ROLE_USER"));

        Usuario usuario = new Usuario(cadastroUsuarioDTO.nomeUsuario(),
                cadastroUsuarioDTO.sobrenomeUsuario(),
                cadastroUsuarioDTO.emailUsuario(),
                senhaEncode,
                roleUser,
                loja
        );

        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorId(Long idUsuario){
        return usuarioRepository.findById(idUsuario).get();
    }
}
