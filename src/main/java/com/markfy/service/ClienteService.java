package com.markfy.service;

import com.markfy.models.Cliente;
import com.markfy.models.Produto;
import com.markfy.repository.ClienteRepository;
import com.markfy.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public List<Cliente> listarClientesDaLojaDoUsuario(Long idUsuario) {
        Long idLoja = usuarioRepository.findById(idUsuario).get().getLoja().getIdLoja();
        return clienteRepository.findClientesByLojaId(idLoja);
    }
}
