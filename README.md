# Notification Producer

Aplicação usando Spring Web, Spring for RabbitMQ que faz produção de mensagem para uma fila do RabbitMQ.

### Pré-Requisitos

* Instalar/configurar o Java (versão 11);
* Ter alguma IDE para desenvolvimento, por exemplo, IntelliJ;
* Ter o Git e o Docker instalados.

### Download e Iniciando

As instruções abaixo possibilitará você fazer o download e configuração do projeto para sua máquina para fins de estudo, desenvolvimento, testes.

Fazer o clone do projeto do github e importar na IDE:
> git clone https://github.com/felipesm/notification-producer.git

Executar o seguinte comando para fazer download da imagem do RabbitMQ:
> $ docker run -d --hostname localhost --name rabbitmq -p 15672:15672 -p 5672:5672 rabbitmq:3-management

Ao startar a aplicação ela subirá na porta 8081. Em seguida poderá ser usado o Postman para fazer a requisição no endpoint que produzirá a mensagem.

* **URL**

  http://localhost:8081/producer/notification

* **Método**
  
  POST
```json
{
    "subject": "subject",
    "message": "message lorem ipsum",
    "from": "message from",
    "to": "message to",
    "createdAt": "yyyy-MM-ddTHH:mm:ss.000"
}
```

*  **Resposta Sucesso**

   Code: 202 - Accepted<br>
