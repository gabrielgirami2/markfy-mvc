# Markfy

Markfy é uma aplicação desenvolvida em Java, utilizando o padrão MVC, para gerenciamento de [insira a funcionalidade principal da aplicação, ex: "tarefas de marketing"]. Este repositório contém o código-fonte da aplicação e configurações para CI/CD usando Azure DevOps (Azure Pipelines) e GitHub Actions para deploy em um Azure Web App.

## Requisitos

Para reproduzir o build e deploy desta aplicação, o professor deverá garantir que os seguintes requisitos estejam configurados:

- Conta no [Azure DevOps](https://azure.microsoft.com/services/devops/) com permissão para criar pipelines.
- Conta no [Azure Web App](https://azure.microsoft.com/services/app-service/web/) com permissão para realizar deploy.
- Conta no [GitHub](https://github.com/) com acesso a este repositório.

## Estrutura do Repositório

- **/src**: Contém o código-fonte da aplicação.
- **/azure-pipelines.yml**: Configuração do Azure DevOps para o pipeline de build.
- **/.github/workflows**: Contém o workflow GitHub Actions para deploy na Azure Web Apps.
- **/README.md**: Instruções de setup e deploy para a aplicação.

## Executando o Build e o Deploy

### Para Rodar Apenas o Build

Para executar somente o processo de build sem o deploy, acesse o Azure DevOps e inicie o build manualmente:

- Link para o pipeline no Azure DevOps: [Acessar pipeline](https://dev.azure.com/brunofbpaula-fiap/AulaDevops/_build)

### Para Rodar o Build e o Deploy

Para executar o build e o deploy simultaneamente:

1. Faça uma alteração no código ou arquivo do repositório (ex.: ajuste de comentário).
2. Realize um push das alterações no repositório GitHub.
3. Isso acionará automaticamente o workflow GitHub Actions, que executará o processo de build e, em seguida, o deploy para o Azure Web App.

O workflow do GitHub Actions está configurado para rodar automaticamente a cada push na branch principal.

## Configurando o Azure DevOps Pipeline

### Passo 1: Importar o Repositório

1. Faça o fork deste repositório para seu GitHub.
2. Importe o repositório para o Azure DevOps.

### Passo 2: Configurar o Pipeline

1. No Azure DevOps, vá para a seção **Pipelines** e clique em **Create Pipeline**.
2. Escolha o repositório da Markfy.
3. Selecione a opção para configurar o pipeline com base no arquivo `azure-pipelines.yml` presente no repositório.
4. Execute o pipeline para confirmar que o build ocorre com sucesso.

O pipeline no Azure DevOps está configurado para compilar o projeto Java e gerar os artefatos necessários para o deploy.

## Configurando o Deploy com GitHub Actions e Azure Web App

O GitHub Actions está configurado para iniciar automaticamente o deploy para o Azure Web App sempre que há mudanças no código principal. Certifique-se de que as permissões e configurações do Azure Web App estão corretamente configuradas no GitHub Actions.

### Passo 1: Configurar Segredos do GitHub

1. No repositório do GitHub, vá em **Settings** > **Secrets**.
2. Adicione as seguintes variáveis de ambiente:
   - `AZURE_WEBAPP_NAME`: Nome do Web App configurado no Azure.
   - `AZURE_WEBAPP_PACKAGE_PATH`: Caminho do artefato para deploy (ex: `target/*.jar`).
   - `AZURE_CREDENTIALS`: Credenciais JSON para autenticação com o Azure (o JSON pode ser gerado nas configurações da conta Azure).

### Passo 2: Configurar e Executar o Workflow de Deploy

O arquivo `.github/workflows/deploy.yml` contém o workflow GitHub Actions para realizar o deploy no Azure Web App. Para rodá-lo:

1. Após configurar os segredos no GitHub, faça um commit com qualquer alteração no código (ex.: alterando um comentário).
2. Isso iniciará o workflow GitHub Actions que executará as seguintes etapas:
   - Build do projeto.
   - Deploy para o Azure Web App.

Acompanhe a execução do workflow em **Actions** no GitHub para verificar o status de cada etapa.

## Executando Testes

Para validar o funcionamento da aplicação, siga os passos abaixo:

1. Acesse o Azure Web App pelo link fornecido pelo serviço.
2. [Insira aqui instruções de teste, como URLs específicas ou ações na aplicação para verificar se está tudo funcionando conforme esperado.]

---

## Problemas Comuns e Soluções

- **Erro de autenticação no GitHub Actions**: Verifique se as credenciais (`AZURE_CREDENTIALS`) estão corretamente configuradas.
- **Build falhando no Azure DevOps**: Certifique-se de que todas as dependências do projeto estão configuradas corretamente no arquivo `pom.xml` (ou equivalente) do projeto Java.

## Contato

Para dúvidas ou problemas na configuração, entre em contato com o time de desenvolvimento da Markfy.
