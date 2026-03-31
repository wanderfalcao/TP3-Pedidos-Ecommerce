# Refatoração — Sistema de Pedidos E-commerce

[![Build e Testes](https://github.com/wanderfalcao/TP3-Pedidos-Ecommerce/actions/workflows/build.yml/badge.svg)](https://github.com/wanderfalcao/TP3-Pedidos-Ecommerce/actions/workflows/build.yml)
[![Java](https://img.shields.io/badge/Java-21-blue?logo=openjdk)](https://openjdk.org/projects/jdk/21/)
[![Maven](https://img.shields.io/badge/build-Maven-red?logo=apachemaven)](https://maven.apache.org/)
[![JUnit 5](https://img.shields.io/badge/tests-JUnit%205-green?logo=junit5)](https://junit.org/junit5/)

Trabalho de refatoração de um sistema legado responsável por gerar faturas e enviar emails de confirmação de pedidos.

## O código original

O sistema funcionava, mas foi escrito de forma apressada. Tudo estava concentrado na classe `Order`: dados do cliente como atributos públicos, três listas paralelas sem nenhuma garantia de consistência entre elas, cálculo de total misturado dentro do método de impressão e chamada direta ao `EmailService` exposta para qualquer parte do código. Além disso, existia uma classe `DiscountPolicy` que nunca chegou a ser usada.

O maior problema prático era as listas paralelas — `products`, `quantities` e `prices` precisavam ser manipuladas juntas e em sincronia, mas nada no código impedia que ficassem fora de ordem ou com tamanhos diferentes.

## Como executar

```bash
mvn test
```

Requer Java 21+ e Maven instalados.

## Refatorações aplicadas

**Extração de `Client`:** nome e email do cliente saíram de `Order` para uma classe própria. Além de reduzir a responsabilidade de `Order`, permite reutilizar `Client` em outras partes do sistema no futuro.

**Extração de `Item`:** as três listas paralelas foram substituídas por uma única `List<Item>`. Cada item carrega seu próprio produto, quantidade e preço, e calcula o próprio subtotal. Com isso, não existe mais a possibilidade de inconsistência entre as listas.

**Encapsulamento de `Order`:** todos os atributos passaram a `private final`. O único ponto de entrada para adicionar produtos é `addItem(Item)`. A taxa de desconto, que antes era pública e podia ser alterada de fora, agora é definida no construtor e não muda.

**Extração de métodos de cálculo:** o cálculo de subtotal e desconto saiu do meio de `printInvoice()` para métodos privados. O `printInvoice()` passou a apenas chamar esses métodos, tornando o fluxo mais fácil de seguir. O método `calculateTotal()` ficou público para permitir testes diretos.

**Remoção de `DiscountPolicy`:** a classe existia mas nunca era chamada. A lógica de desconto já estava em `Order` de forma mais coesa, então `DiscountPolicy` foi removida sem perda alguma.

**Injeção de dependência em `EmailService`:** o método `sendEmail` deixou de ser `static`. A instância de `EmailService` agora é recebida no construtor de `Order`, o que permite substituí-la por um mock em testes futuros. O Javadoc documenta que o serviço é de uso interno e não deve ser chamado diretamente pelo código cliente.

**Eliminação de feature envy em `Item`:** `Item` ganhou o método `toDisplayString()`, que centraliza a formatação da própria linha na nota fiscal. O `printInvoice()` de `Order` parou de acessar três campos de `Item` para montar a string, delegando essa responsabilidade ao próprio objeto.

## Estrutura final

```
src/main/java/com/ecommerce/
  App.java           ponto de entrada da aplicação
  Client.java        dados do cliente (nome e email)
  Item.java          produto com quantidade, preço, subtotal e formatação para exibição
  Order.java         pedido com cálculo de total e emissão de fatura
  EmailService.java  envio de email (usado internamente por Order)

src/test/java/com/ecommerce/
  ClientTest.java    1 teste cobrindo armazenamento de dados do cliente
  ItemTest.java      4 testes cobrindo subtotal e getters de Item
  OrderTest.java     9 testes cobrindo cálculo de total, desconto e imutabilidade
```

A cobertura é monitorada automaticamente pelo JaCoCo a cada push, com o relatório exibido diretamente na pipeline do GitHub Actions.
