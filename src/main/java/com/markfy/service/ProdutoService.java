package com.markfy.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.markfy.config.QueueSender;
import com.markfy.dto.produto.AlterarProdutoDTO;
import com.markfy.dto.produto.CadastroProdutoDTO;
import com.markfy.dto.produto.ProdutoQueueDTO;
import com.markfy.models.Loja;
import com.markfy.models.Produto;
import com.markfy.repository.ProdutoRepository;
import com.markfy.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private QueueSender queueSender;

    @Autowired
    private ObjectMapper objectMapper;

    public Produto cadastrar(Long idUsuario, CadastroProdutoDTO cadastroUsuarioDTO) throws JsonProcessingException {
        Loja loja = usuarioRepository.findById(idUsuario).get().getLoja();
        Produto produto = new Produto(cadastroUsuarioDTO, loja);
        produtoRepository.save(produto);

        String produtoJson = objectMapper.writeValueAsString(produto);
        System.out.println("Enviando produto para fila: " + produtoJson);
        queueSender.send(produtoJson);

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

    @RabbitListener(queues = {"${queue.name.consumer}"})
    public void abaterEstoque(@Payload String queueMessage) throws JsonProcessingException {
        System.out.println("Produto comprado: " + queueMessage);

        try {
            ProdutoQueueDTO produtoQueue = objectMapper.readValue(queueMessage, ProdutoQueueDTO.class);

            Produto produto = produtoRepository.findById(produtoQueue.idProduto()).get();

            Integer novoEstoque = produto.getEstoque() - produtoQueue.quantidade();
            produto.setEstoque(novoEstoque);

            produtoRepository.save(produto);

            System.out.println("novo estoque: " + produtoRepository.findById(produtoQueue.idProduto()).get().getEstoque());
        }catch (Exception e){
            System.out.println("Erro ao atualizar o estoque a partir da compra");
        }

    }

    public Integer contarProdutos() {
        return produtoRepository.totalProdutos();
    }

    public Produto buscarPorId(Long idProduto) {
        return produtoRepository.findById(idProduto).get();
    }
}
