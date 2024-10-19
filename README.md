# Hurr - Sistema de Aluguel de Carros

## Introdução

O projeto "Hurr" visa desenvolver um sistema de aluguel de carros que conecta proprietários de veículos a usuários interessados em alugar. A plataforma busca solucionar a falta de uma solução eficiente e segura para aluguel de veículos entre particulares, atendendo à crescente demanda por serviços personalizados e acessíveis.

### Objetivos do Projeto

- **Objetivo Geral**: Criar uma experiência intuitiva e eficiente para proprietários e usuários de veículos.

- **Objetivos Específicos**:
    - Implementar funcionalidades de cadastro de proprietários e pesquisa de veículos disponíveis.
    - Desenvolver um sistema de reserva online seguro e confiável.
    - Criar um módulo de autenticação e perfis de usuário.
    - Integrar um sistema de pagamento online para transações de aluguel.
    - Implementar um sistema de avaliações e comentários para promover a transparência.

## Especificações Iniciais do Software

### Escopo do Produto

Desenvolver uma plataforma intuitiva que permita a locação de carros de forma segura e prática.

### Funcionalidades do Produto

- **Cadastro de Proprietários**: Permite que proprietários cadastrarem seus veículos.
- **Pesquisa e Reserva**: Facilita a busca por carros disponíveis com base em diferentes critérios.
- **Processo de Reserva**: Sistema online que ajuda na escolha do veículo e confirmação da reserva.
- **Autenticação e Perfil do Usuário**: Módulo seguro para gerenciar informações pessoais.
- **Sistema de Pagamento**: Integração segura para transações de aluguel.
- **Avaliações e Comentários**: Permite feedback sobre a experiência de aluguel.

### Ambiente Operacional e Tecnologias

- **Plataforma**: Acessível via web.
- **Sistema Operacional**: Windows, macOS, Linux.
- **Tecnologias**: Java, Spring Boot, Spring Data JPA, Spring Security, Spring OAuth, Lombok, Flyway, Maven, PostgreSQL.

## Metodologia

O desenvolvimento segue uma abordagem ágil, utilizando práticas de Scrum, com entregas incrementais e feedback contínuo dos stakeholders.

## Desenvolvimento

### Diagrama ER (Diagrama de Entidade-Relacionamento)

O Diagrama ER representa a estrutura do banco de dados, incluindo as principais entidades e seus relacionamentos, como Proprietário, Veículo, Locatário, Reserva e Solicitação de Retirada.

### Arquitetura do Software

A arquitetura é baseada em microserviços, permitindo escalabilidade e manutenção futura, com componentes principais como:

- **Frontend**: Desenvolvido em Angular.
- **Backend**: Implementado em Spring Boot.
- **Banco de Dados**: PostgreSQL.
- **Sistema de Pagamento**: Integração com MercadoPago.
- **Infraestrutura**: Utiliza Docker e AWS.

### Telas do Sistema

1. **Tela de Cadastro de Veículos**: Permite ao proprietário adicionar um novo veículo.
2. **Tela de Busca de Veículos**: Permite que os usuários filtrem veículos disponíveis.
3. **Tela de Detalhes da Reserva**: Mostra informações detalhadas do veículo e permite a confirmação da reserva.

### Código-Fonte Relevante

O código-fonte para as principais funcionalidades do sistema está disponível nos repositórios abaixo.

### Link para o Repositório

- [Hurr - Frontend](https://github.com/edersonsergiocoelho/hurr-ui)
- [Hurr - Backend](https://github.com/edersonsergiocoelho/hurr-api)

## Considerações Finais

O sistema "Hurr" demonstrou a viabilidade de uma plataforma que conecta proprietários e locatários de forma prática e segura. A aplicação de princípios da engenharia de software garantiu uma solução que atende aos requisitos propostos, contribuindo para uma economia compartilhada. Futuras melhorias incluem a implementação de uma aba de assinatura de carros e a oferta de seguros.

## Referências Bibliográficas

- Gamma, E.; Helm, R.; Johnson, R.; Vlissides, J. Design Patterns: Elements of Reusable Object-Oriented Software. Addison-Wesley, 1994.
- Pressman, R. S. Engenharia de Software: Uma Abordagem Profissional. McGraw-Hill, 2011.
- Sommerville, I. Engenharia de Software. Pearson Addison-Wesley, 2003.
- [Perfil no GitHub de Joe Grandja](https://github.com/jgrandja).
- [spring-boot-angular-oauth2-social-login-demo](https://github.com/JavaChinna/spring-boot-angular-oauth2-social-login-demo).
- [Turo - Aluguel de Carros Online](https://turo.com/).

# Instruções para Execução do Projeto

Para executar o projeto "Hurr - Sistema de Aluguel de Carros", siga os passos abaixo:

## 1. Pré-requisitos

- **Java 21 ou superior:** Verifique se você tem o Java instalado. Você pode baixar a versão mais recente do [site oficial do Java](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html).
- **IntelliJ IDEA:** Recomendamos o uso do [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) como IDE para desenvolvimento. A versão Community é suficiente para este projeto.
- **PostgreSQL:** Certifique-se de ter o [PostgreSQL](https://www.postgresql.org/download/) instalado e em execução.

## 2. Configuração do Ambiente

1. **Clone o Repositório:**
   Abra o terminal e clone o repositório do projeto:
   ```bash
   git clone https://github.com/edersonsergiocoelho/hurr-api.git

## 2. Configuração do Ambiente

1. **Importe o Projeto no IntelliJ:**
  - Abra o IntelliJ IDEA.
  - Selecione **File > New > Project from Existing Sources...** e navegue até a pasta do projeto clonado.
  - Selecione a opção para importar o projeto como um projeto Maven.

2. **Configurar o Banco de Dados:**
  - Crie um banco de dados no PostgreSQL para o projeto (por exemplo, `hurr_db`).
  - Edite o arquivo `src/main/resources/application.properties` para configurar a conexão com o banco de dados. Exemplo de configuração:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/hurr
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    ```

3. **Instalar Dependências:**
   O IntelliJ deve automaticamente baixar as dependências do Maven. Se não, você pode forçar a atualização clicando com o botão direito na árvore do projeto e selecionando **Maven > Reload Project**.

## 3. Executando o Projeto

1. **Executar o Servidor:**
  - Localize a classe principal do projeto, que geralmente está na pasta `src/main/java/com/seu_pacote/HurrApplication.java`.
  - Clique com o botão direito na classe e selecione **Run 'HurrApplication'**. Isso iniciará o servidor Spring Boot.

2. **Testar a API:**
  - Com o servidor em execução, você pode testar as APIs usando ferramentas como Postman ou cURL. A API estará disponível em `http://localhost:8080`.
