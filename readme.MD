<h1>Bank Application</h1>

Este projeto tem como objetivo simular as operações essenciais de um banco, oferecendo uma experiência completa e realista para gerenciar contas, transações e extratos bancários. A aplicação inclui funcionalidades como:

Criação de Contas: Notificações automáticas via email ao criar uma nova conta, proporcionando uma comunicação imediata com os usuários.

Gestão de Transações: Realize transferências de dinheiro entre contas, com verificação de saldo e atualização em tempo real. Cada transação é segura e registrada para futuras consultas.

Geração de Extratos Bancários: Acesse extratos detalhados das contas através do email, com a opção de baixar o documento em formato PDF, facilitando o acompanhamento das finanças pessoais.

Este projeto é ideal para quem deseja entender e explorar a lógica por trás dos sistemas bancários, implementando funcionalidades comuns em um ambiente controlado. Desenvolvido com foco em boas práticas de programação e arquitetura de software, como o uso do framework Spring Boot e integração com serviços de email.

## Tecnologias

- [Spring Boot](https://spring.io/projects/spring-boot)
- [MySQL](https://www.mysql.com/)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Spring Starter Mail](https://docs.spring.io/spring-framework/reference/integration/email.html)
- [Swagger](https://swagger.io/)
- [iText](https://mvnrepository.com/artifact/com.itextpdf/itextpdf/5.5.13.4)



## Como Executar

- Clonar repositório git
```
git clone git@github.com:igorbarret0/bank-application.git
```

- Construir o projeto:
```
./mvnw clean package
```

A API poderá ser acessada em [localhost:8080](http://localhost:8080).

## API Endpoints

Para fazer as requisições HTTP abaixo, foi utilizada a ferramenta [Postman](https://www.postman.com/)

-  ACCOUNT
```
POST /account - Criar conta

{
    "name": "Vivian",
    "lastName": "Afogu",
    "otherName": "Chima",
    "gender": "Male",
    "address": "12, Infinity House",
    "stateOfOrigin": "Bahia",
    "email": "testemailsenderhls@gmail.com",
    "phoneNumber": "829348234",
    "alternativePhone": "091229047324"

}
```

```
GET /account/balanceEnquiry - Consultar balanço

{
     "accountNumber": "2024996229"
}
```

```
GET /account/nameEnquiry - Consultar dono da conta

{
     "accountNumber": "2024996229"
}
```

```
POST /account/credit - Creditar uma conta

{
     "accountNumber": "2024938487",
    "amount": 30.0
}
```

```
POST /account/debit - Debitar uma conta

{
    "accountNumber": "2024996229",
    "amount": 20.0
}
```

```
POST /account/transfer - Realizar transferências

{
    "accountNumberSender": "2024938487",
    "accountNumberReceiver": "2024996229",
    "amount": 10.0
}
```

- BANK STATEMENT

```
GET /bankStatement?accountNumber=2024996229&startDate=2024-08-28&endDate=2024-08-29
```



