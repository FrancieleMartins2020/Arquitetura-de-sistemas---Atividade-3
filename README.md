# 🛒 Atividade 3 – Arquitetura Orientada a Eventos (E-commerce)

> Projeto desenvolvido para demonstrar **Arquitetura Orientada a Eventos**, utilizando **Spring Boot**, **MongoDB Change Streams** e **RabbitMQ**, simulando o fluxo de compra de um e-commerce moderno.

---

## 📌 Arquitetura da Solução

<p align="center">
  <img src="docs/arquitetura.png" alt="Arquitetura do Sistema" width="800"/>
</p>

**Fluxo resumido:**

1. O usuário realiza uma compra no **ecommerce.loja.web**
2. A venda é persistida no **MongoDB**
3. O **Change Stream** detecta o evento de inserção
4. O **monitorador.loja.web** processa o evento
5. Mensagens são publicadas nas filas do **RabbitMQ**
   - Pagamento
   - E-mail

---

## 🧩 Serviços do Projeto

### 🛍️ ecommerce.loja.web
- Exibe o catálogo de produtos
- Consome a API de catálogo (`/catalogo`)
- Finaliza compras
- Persiste vendas no MongoDB

### 👀 monitorador.loja.web
- Escuta eventos de inserção no MongoDB (Change Streams)
- Converte documentos em objetos Java
- Publica mensagens no RabbitMQ
  - Fila de pagamento
  - Fila de e-mail

---

## 🛠️ Tecnologias Utilizadas

- Java 21  
- Spring Boot 3  
- Spring Data MongoDB  
- MongoDB Change Streams  
- Spring AMQP (RabbitMQ)  
- RabbitMQ  
- Docker  
- Docker Compose  

---

## 🚀 Subindo o Ambiente

### 🔴 Remover containers antigos (se existirem)

```bash
docker rm -f rabbitmq_local
