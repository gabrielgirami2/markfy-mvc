package com.markfy.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.markfy.dto.cliente.ResponseOllamaDTO;
import com.markfy.models.Cliente;
import com.markfy.models.Produto;
import com.markfy.repository.ClienteRepository;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.api.OllamaModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnaliseService {

    @Autowired
    private ChatModel chatModel;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ObjectMapper objectMapper;


    public ResponseOllamaDTO analisar(Produto produto) throws JsonProcessingException {
        List<Cliente> clientes = clienteRepository.findAll();
        String pergunta = "Levando em consideração que tenho os seguintes clientes: "
                + clientes
                + ". E o seguinte produto: "
                + produto
                + ". Qual desses clientes é o mais provavel de comprar este produto?"
                + " Me forneça as informações do cliente escolhido em um JSON do seguinte tipo: "
                + "{ 'nome': string, 'sobrenome': string, 'email': string, 'cpf': string }"
                + "Atenção! Não responda NADA além do JSON e inclua todas as propriedades (nome, sobrenome, email e cpf).";

        System.out.println(pergunta);

        ChatResponse response = chatModel.call(
                new Prompt(
                        pergunta,
                        OllamaOptions.builder()
                                .withModel(OllamaModel.LLAMA3_2)
                                .withTemperature(0.4)
                                .build()
                )
        );

        System.out.println("Resposta do Ollama: " + response.getResult().getOutput().getContent());

        ResponseOllamaDTO responseOllamaDTO = objectMapper.readValue(response.getResult().getOutput().getContent(), ResponseOllamaDTO.class);

        return responseOllamaDTO;
    }
}
