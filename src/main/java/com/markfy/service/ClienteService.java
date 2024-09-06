package com.markfy.service;

import com.markfy.dto.cliente.AlterarClienteDTO;
import com.markfy.dto.cliente.CadastroClienteDTO;
import com.markfy.dto.produto.AlterarProdutoDTO;
import com.markfy.dto.produto.CadastroProdutoDTO;
import com.markfy.models.Cliente;
import com.markfy.models.Loja;
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

    public Cliente cadastrar(Long idUsuario, CadastroClienteDTO cadastroClienteDTO) {
        Loja loja = usuarioRepository.findById(idUsuario).get().getLoja();
        Cliente cliente = new Cliente(cadastroClienteDTO, loja);
        clienteRepository.save(cliente);
        return cliente;
    }

    public Cliente alterar(Long id, AlterarClienteDTO alterarClienteDTO) {
        Cliente clienteById = clienteRepository.findById(id).get();
        clienteById.alterar(alterarClienteDTO);
        clienteRepository.save(clienteById);
        return clienteById;
    }

    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }

}
