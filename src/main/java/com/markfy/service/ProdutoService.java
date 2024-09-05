package com.markfy.service;

import com.markfy.dto.produto.AlterarProdutoDTO;
import com.markfy.dto.produto.CadastroProdutoDTO;
import com.markfy.models.Loja;
import com.markfy.models.Produto;
import com.markfy.repository.ProdutoRepository;
import com.markfy.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Produto cadastrar(Long idUsuario, CadastroProdutoDTO cadastroUsuarioDTO) {
        Loja loja = usuarioRepository.findById(idUsuario).get().getLoja();
        Produto produto = new Produto(cadastroUsuarioDTO, loja);
        produtoRepository.save(produto);
        return produto;
    }

    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    public Produto alterar(Long id, AlterarProdutoDTO alterarProdutoDTO) {
        Produto produtoById = produtoRepository.findById(id).get();

        produtoById.alterar(alterarProdutoDTO);
        produtoRepository.save(produtoById);
        return produtoById;
    }

    public void deletar(Long id) {
        produtoRepository.deleteById(id);
    }

    public List<Produto> listarProdutosDaLojaDoUsuario(Long idusuario) {
        Long idLoja = usuarioRepository.findById(idusuario).get().getLoja().getIdLoja();
        return produtoRepository.findProdutosByLojaId(idLoja);
    }
}
