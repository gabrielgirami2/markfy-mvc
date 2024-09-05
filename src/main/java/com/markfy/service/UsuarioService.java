package com.markfy.service;

import com.markfy.dto.usuario.AlterarUsuarioDTO;
import com.markfy.dto.usuario.CadastroUsuarioDTO;
import com.markfy.dto.usuario.UsuarioLoginDTO;
import com.markfy.models.Loja;
import com.markfy.models.Usuario;
import com.markfy.repository.LojaRepository;
import com.markfy.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private LojaRepository lojaRepository;

    public Usuario cadastrar(CadastroUsuarioDTO cadastroUsuarioDTO) throws Exception {
        Usuario usuarioByEmail = usuarioRepository.findByEmailUsuario(cadastroUsuarioDTO.emailUsuario());

        if(usuarioByEmail != null){
            throw new Exception("O usuário informado já possui um cadasto.");
        }

        Loja lojaById = lojaRepository.getReferenceById(cadastroUsuarioDTO.idLoja());
        System.out.println(lojaById);
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

    public Usuario entrar(UsuarioLoginDTO usuarioLoginDTO) throws Exception {
        Usuario usuario = usuarioRepository.findByEmailUsuario(usuarioLoginDTO.email());

        if(usuario == null || !usuarioLoginDTO.senha().equals(usuario.getSenha())){
            throw new Exception("Usuário ou senha incorretos");
        }

        return usuario;
    }
}
