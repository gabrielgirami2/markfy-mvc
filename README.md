# Markfy

## Descrição do Projeto
**Markfy** é um sistema desenvolvido para gerenciar clientes, produtos e realizar análises de marketing de lojas de roupas. A aplicação utiliza Java com Spring MVC e integra-se a contêineres Docker para fornecer suporte a serviços adicionais essenciais para o funcionamento do sistema.

---

## Integrantes
- Kayque Lima - RM550782
- Gabriel França - RM551905
- Bruno Francisco - RM552226
- Edward de Lima - RM98676

## Nome do Grupo
Markfy

---

## Código Fonte
O código-fonte do projeto pode ser encontrado neste repositório. Certifique-se de clonar o repositório e seguir as instruções para rodar a aplicação localmente.

## Instruções de Instalação e Execução

### Pré-requisitos
- Java 21 ou superior
- Maven
- Docker e Docker Compose

### Passos para Rodar Localmente
1. **Clonar o Repositório**
   ```bash
   git clone https://github.com/MarkfySoftware/markfy-mvc.git
   cd Markfy

2. **Configuração dos Contêineres Docker** Execute os seguintes comandos para iniciar os serviços necessários:
   ```bash
   docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:4.0-management
   docker run -d -v ollama:/root/.ollama -p 11434:11434 --name ollama ollama/ollama
   docker exec -it ollama ollama run llama3.2

3. **Compilar e Executar o Projeto** No diretório do projeto, compile e execute a aplicação:
   ```bash
   mvn clean install
   mvn spring-boot:run

4. **Acessar a Aplicação**
   - A aplicação estará disponível em ``http://localhost:8080``

## Link do Vídeo de Demonstração

Assista à apresentação e demonstração do projeto [neste link](http://olá)