# Markfy

Markfy é uma aplicação desenvolvida em Java, utilizando o padrão MVC, para gerenciamento de lojas de roupa. Este repositório contém o código-fonte da aplicação e configurações para CI/CD usando Azure DevOps (Azure Pipelines) e GitHub Actions para deploy em um Azure Web App.

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

O workflow do GitHub Actions está configurado para rodar automaticamente a cada push na branch devops.

## Executando Testes

Para validar o funcionamento da aplicação, siga os passos abaixo:

1. Acesse o Azure Web App pelo link fornecido pelo serviço.

---

## Problemas Comuns e Soluções

- **Erro de autenticação no GitHub Actions**: Verifique se as credenciais (`AZURE_CREDENTIALS`) estão corretamente configuradas.
- **Build falhando no Azure DevOps**: Certifique-se de que todas as dependências do projeto estão configuradas corretamente no arquivo `pom.xml` (ou equivalente) do projeto Java.

## Contato

Para dúvidas ou problemas na configuração, entre em contato com o time de desenvolvimento da Markfy.
