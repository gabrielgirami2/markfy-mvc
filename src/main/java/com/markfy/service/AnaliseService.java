package com.markfy.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.markfy.dto.cliente.ResponseOllamaDTO;
import com.markfy.dto.ollama.OllamaResponseDTO;
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
    private ObjectMapper objectMapper;





    public OllamaResponseDTO analisarProduto(Produto produto, List<Cliente> clientes) throws Exception {
        String pergunta = "Você deve analisar as características dos seguintes clientes em relação ao produto "
                + produto.getNome() + ", que tem o preço de R$ " + produto.getValor() + ".\n"
                + "Por favor, considere os seguintes fatores ao identificar o cliente com a maior probabilidade de compra: \n"
                + "- IDADE\n"
                + "- Estado civil\n"
                + "- Nível educacional\n"
                + "- Ocupação\n"
                + "- Renda anual\n\n"
                + "Aqui estão os clientes:\n" + clientes + "\n"
                + "Produto: " + produto + "\n\n"
                + "Estruture sua análise no seguinte formato JSON: \n"
                + "{\n"
                + "  'nomeCliente': 'string',\n"
                + "  'nomeProduto': 'string',\n"
                + "  'motivoEscolha': 'string'\n"
                + "}\n"
                + "Onde 'motivoEscolha' deve explicar por que este produto é a melhor escolha para o cliente. Responda estritamente no formato JSON e não inclua mais nenhuma informação além disso.";

        try {
            ChatResponse response = chatModel.call(
                    new Prompt(
                            pergunta,
                            OllamaOptions.builder()
                                    .withModel(OllamaModel.LLAMA3_2)
                                    .withTemperature(0.5)
                                    .build()
                    )
            );

            System.out.println("Resposta do Ollama: \n" + response.getResult().getOutput().getContent());
            return objectMapper.readValue(response.getResult().getOutput().getContent(), OllamaResponseDTO.class);
        }catch (Exception e){
            throw new Exception("Ocorreu um erro ao realzar a análise: " + e.getMessage());
        }
    }

    public OllamaResponseDTO analisarCliente(Cliente cliente, List<Produto> produtos) throws Exception {
        String pergunta = "Você deve analisar as características do seguinte cliente:\n"
                + cliente + "\n\n"
                + "E os seguintes produtos disponíveis:\n"
                + produtos + "\n\n"
                + "Com base nas características do cliente, como IDADE, estado civil, nível educacional, ocupação e renda anual, me forneça o produto que melhor combina com o cliente acima.\n"
                + "Estruture sua resposta no seguinte formato JSON:\n"
                + "{\n"
                + "  'nomeCliente': 'string',\n"
                + "  'nomeProduto': 'string',\n"
                + "  'motivoEscolha': 'string'\n"
                + "}\n"
                + "Onde 'motivoEscolha' deve explicar por que este produto é a melhor escolha para o cliente. Responda estritamente no formato JSON e não inclua mais nenhuma informação além disso.";

        try {
            ChatResponse response = chatModel.call(
                    new Prompt(
                            pergunta,
                            OllamaOptions.builder()
                                    .withModel(OllamaModel.LLAMA3_2)
                                    .withTemperature(0.5)
                                    .build()
                    )
            );

            System.out.println("Resposta do Ollama: \n" + response.getResult().getOutput().getContent());
            return objectMapper.readValue(response.getResult().getOutput().getContent(), OllamaResponseDTO.class);
        }catch (Exception e){
            throw new Exception("Ocorreu um erro ao realzar a análise: " + e.getMessage());
        }
    }
}
