📦 Atividade 3 – Arquitetura Orientada a Eventos (E-commerce)

Este repositório contém a implementação da Atividade 3, que demonstra uma arquitetura baseada em eventos, utilizando MongoDB Change Streams, RabbitMQ e múltiplas aplicações Spring Boot, simulando um fluxo de compra em um e-commerce.

🧩 Visão Geral da Arquitetura

A solução é composta por:

ecommerce.loja.web
Aplicação principal do e-commerce, responsável por:

Exibir produtos

Finalizar compras

Persistir vendas no MongoDB

monitorador.loja.web
Serviço que:

Escuta eventos de inserção no MongoDB via Change Streams

Processa eventos de venda

Publica mensagens nas filas de pagamento e e-mail no RabbitMQ

MongoDB (Replica Set)
Utilizado para persistência e para viabilizar Change Streams.

RabbitMQ
Responsável pela comunicação assíncrona entre os serviços.

🛠️ Tecnologias Utilizadas

Java 21

Spring Boot 3.x

Spring Data MongoDB

Spring AMQP (RabbitMQ)

MongoDB

RabbitMQ

Docker & Docker Compose

📋 Pré-requisitos

Antes de iniciar, certifique-se de ter instalado:

Docker

Docker Compose

Java 21

Maven

🚀 Passo a Passo para Subir o Ambiente
🔹 1. Remover containers antigos (se existirem)
docker rm -f rabbitmq_local

🔹 2. Subir os containers via Docker Compose
docker compose up -d


Esse comando sobe os serviços definidos no docker-compose.yml.

🔹 3. Subir o RabbitMQ manualmente (caso necessário)
docker run -d \
  --name rabbitmq \
  -p 5672:5672 \
  -p 15672:15672 \
  rabbitmq:3-management


Interface Web: http://localhost:15672

Usuário padrão: guest

Senha padrão: guest

🔹 4. Subir o MongoDB manualmente (caso necessário)
docker run -d \
  --name mongo-loja \
  -p 27017:27017 \
  mongo:latest

🔹 5. Subir apenas o serviço MongoDB via Docker Compose
docker-compose up -d mongodb

🔁 Configuração do Replica Set (Obrigatório para Change Streams)
🔹 6. Acessar o MongoDB
docker exec -it mongo_local mongosh

🔹 7. Inicializar o Replica Set
rs.initiate()

🔹 8. Verificar o status
rs.status()

🔹 9. Reconfigurar o Replica Set (se necessário)

Caso o replica set não esteja funcionando corretamente:

rs.initiate()

rs.reconfig({
  _id: "rs0",
  members: [
    { _id: 0, host: "localhost:27017" }
  ]
}, { force: true })

rs.status()

🔹 10. Reiniciar o MongoDB
docker restart mongo_local

▶️ Execução das Aplicações

Após o ambiente estar configurado:

Inicie o projeto ecommerce.loja.web

Em seguida, inicie o projeto monitorador.loja.web

Ao finalizar uma compra:

A venda é salva no MongoDB

O Change Stream detecta o evento

O monitorador publica mensagens no RabbitMQ

As filas de pagamento e e-mail recebem os eventos

📌 Observações Importantes

O MongoDB precisa estar em modo Replica Set para que os Change Streams funcionem.

As exchanges do RabbitMQ devem existir antes do envio das mensagens.

Toda a comunicação entre serviços ocorre de forma assíncrona, seguindo o modelo orientado a eventos.

🎯 Objetivo da Atividade

Demonstrar na prática:

Arquitetura orientada a eventos

Uso de Change Streams no MongoDB

Comunicação assíncrona com RabbitMQ

Integração entre múltiplos serviços Spring Boot
