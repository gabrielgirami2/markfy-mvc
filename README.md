# Vídeo de Demonstração

Confira abaixo o vídeo de demonstração da aplicação no YouTube:

[Assista ao vídeo de demonstração no YouTube](https://www.youtube.com/watch?v=hc2LBvjBTe4&t=4s)

---

# Código Fonte (Deploy)

Utilizamos uma organização no GitHub para o versionamento dos códigos, abaixo disponibilizamos o link do repositório da aplicação principal:

[GitHub - MarkfySoftware/markfy-mvc](https://github.com/MarkfySoftware/markfy-mvc)

O Deploy foi feito utilizando o serviço “Spring Apps” da Azure. Abaixo descrevemos o passo a passo para implantação do sistema no ambiente Cloud:

### No console da Azure:

1. Crie um grupo de recursos com o nome **markfy**
2. Selecione o serviço **Spring Apps**, nomeie como **markfy** e provisione
3. Entre na instância e crie um aplicativo que rode com o **Java 21**, o nome do aplicativo deve ser **prod**

### No terminal da sua máquina:

1. Clone o repositório usando o comando:

    ```bash
    git clone https://github.com/MarkfySoftware/markfy-mvc.git
    ```

2. Efetue a autenticação na Azure com o comando:

    ```bash
    az login
    ```

3. Entre na pasta criada pela clonagem do repositório:

    ```bash
    cd markfy-mvc
    ```

4. Gere o .JAR da aplicação com o comando: (certifique-se de ter o Maven instalado)

    ```bash
    mvn clean package
    ```

5. Rode o comando responsável por implantar o .JAR no ambiente da Azure:

    ```bash
    az spring app deploy -s markfy -g markfy -n prod --artifact-path target/markyfy-mvc-0.0.1-SNAPSHOT.jar
    ```

### No console da Azure:

Após o término da implantação, entre no aplicativo e selecione a opção **"Atribuir ponto de entrada"**.

### Link do deploy já realizado pela equipe:

[Markfy - Transforme seu Negócio](#)

---

# Código Fonte (Execução)

Para a execução da aplicação em ambiente local siga os seguintes passos:

### No terminal da sua máquina:

1. Clone o repositório:

    ```bash
    git clone https://github.com/MarkfySoftware/markfy-mvc.git
    ```

2. Entre na pasta gerada:

    ```bash
    cd markfy-mvc
    ```

3. Instale as dependências necessárias: (certifique-se de ter o Maven instalado)

    ```bash
    mvn clean install
    ```

4. Após a instalação, rode o comando abaixo para subir a aplicação:

    ```bash
    mvn spring-boot:run
    ```

5. Com a aplicação no ar, vá até seu navegador e acesse o endereço: [http://localhost:8080/](http://localhost:8080/)

Sinta-se à vontade para navegar pela aplicação! :wink:
