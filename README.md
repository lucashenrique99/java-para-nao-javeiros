# JAVA PARA NÃO JAVEIROS

### Objetivos

Este projeto tem como objetivo ilustrar um projeto real desenvolvido utilizando JAVA, Spring MVC e Thymeleaf abordando os principais conceitos de forma didática para treinamentos. 


### Escopo

O projeto foi construído ilustrando um recurso de cliente, o qual possui uma lista de contas. O resultado final é consittuído de páginas JSPs referentes um CRUD básico de clientes. É possivel inserir, listar, filtrar por texto, editar e excluir clientes.

De maneira mais técnica, este projeto possui exemplos de requisições HTTP GET, POST, PUT, DELETE, adicionando cabeçalhos simulando autenticações, passando query params, parâmetros no corpo das requisições, serialização e desserialização, tratamento de erros e testes unitários.

Para o treinamento, os tópicos a serem abordados são:
- Mostrar o resultado final do projeto, explicando o funcionamento básico;
- Explicar os conceitos principais do projeto;
- Adicionar o campo "ativo" ao recurso de cliente;
- Implementar o fluxo de deletar um cliente;


### Requisitos

- JDK 8+
- Maven
- [Mock API](https://mockapi.io/)

##### Mock API

Será utilizada neste projeto apenas para simular um servico externo. Para executar o projeto com uma conta própria, se cadastre no site, obtenha a URL do seu projeto e então:

1. Procure pela classe **com.everis.helloworld.config.AppConfig**
2. Altere a string **API_URL** na linha 14 com a sua URL obtida

Pronto, é só isso. O seu projeto já irá realizar requisições para a sua conta.

###### Modelagem dos recursos na plataforma


**Relação entre os recursos**

![Modelagem](imagens/modelagem.png)


**Recurso Clientes**

![Modelagem do recurso de clientes](imagens/modelagem_clientes.png)


**Recurso Contas**

![Modelagem do recurso de contas](imagens/modelagem_contas.png)


### Como executar o projeto:

A branch **master** contem o projeto final já implementado com todas as features propostas. Já a branch **hands-on** é a branch utilizada como ponto de partida para o Hands on.

1. Clone este repositório e faça o checkout para a branch desejada;
2. Execute:

- Para unix
```sh
  ./mvnw clean install
```

- Para Batch
```sh
  ./mvnw.cmd clean install
```

3) Para iniciar o projeto
```sh   
   ./mvnw spring-boot:run
```

Vá até o navegador e digite [http://localhost:8080](http://localhost:8080) e voce ja verá a aplicação funcionando.


### Nomeclaturas

A nomeclatura das classes (bem como os pacotes onde se encontram cada uma) seguem alguns padrões de acordo com a sua respectiva função. Estas estão descritas abaixo:

- **config**: gerenciam as configurações do projeto;
- **controller**: gerenciam requisições http, definindo os endpoints, métodos aceitos, parâmetros e os retornos de cada método;
- **helper**: recebem os dados vindos das requisições, chamam os servicos responsáveis por realizar o processamento em APIs externas, recebem o resultado e então encaminham para os mappers modelarem para o retorno esperado, encaminhando para as controllers;
- **service**: realizam as chamadas as APIs externas, serializando e desserializando os retornos;
- **mappers**: responsáveis por converter os objetos do domínio da aplicação em objetos de acordo com o contrato das APIs externas e vice versa;
- **DTOs (Data transfer objects)**: são comumente utilizados para representar os contratos de APIs externas. Possuem a função apenas de trafegar dados;
- **ViewModel**: na prática, possuem uma responsabilidade similar aos DTOs (trafegar dados entre as camadas), entretanto, é utilizado dentro do domínio da aplicação. São utilizadas para enviar dados para as JSPs.
- **Utils**: classes utilitárias diversas. Normalmente são compostas de funções utilitárias como formatações de campos, por exemplo.
- **Exception**: classes para representação de exceções


### Fluxo da informação:

1. **Controller** recebe requisição e encaminha pro **helper**;
2. **Helper** encaminha os parametros recebidos do **controller** para o **mapper**, caso existam;
    * O **mapper** converte os dados em **DTOs** de acordo com o contrato da API externa e devolve para o **helper**;
3. **Helper** chama o **service** associado, enviando os respectivos **DTOs** quando houverem parâmetros;
4. **Service** realiza a requisição utilizando os parametros recebidos, obtém o retorno, desserializa e encaminha para o **helper**;
5. **Helper** por sua vez, recebe o retorno em **DTO** e encaminha para os **mappers**;
6. **Mappers** transformam o retorno em objetos **viewModel** para retorno dentro do domínio da aplicação;
7. **Helper** retorna os dados processados pelo **mapper** para o **controller**;
8. **Controller** responde a requisição passando **viewModels** em um objeto Model ou ModelAndView (objetos do Framework Spring), e redirecionando para a JSP correta;


### Testes Unitátios
Este projeto também contempla o tema de testes unitários. Estes se encontram no diretório **src/test/java/com/everis/helloworld** deste repositório. Cada classe de teste unitário refere-se a uma classe da aplicação, verificando os métodos, retornos esperados, etc.

Como executar todos:
- Para unix
```sh
  ./mvnw test
```

- Para Batch
```sh
  ./mvnw.cmd test
```

