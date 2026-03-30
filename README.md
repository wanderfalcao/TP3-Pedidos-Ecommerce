# Refatoração — Sistema de Pedidos E-commerce

Trabalho de refatoração de um sistema legado responsável por gerar faturas e enviar emails de confirmação de pedidos.

## O código original

O sistema funcionava, mas foi escrito de forma apressada. Tudo estava concentrado na classe `Order`: dados do cliente como atributos públicos, três listas paralelas sem nenhuma garantia de consistência entre elas, cálculo de total misturado dentro do método de impressão e chamada direta ao `EmailService` exposta para qualquer parte do código. Além disso, existia uma classe `DiscountPolicy` que nunca chegou a ser usada.

O maior problema prático era as listas paralelas — `products`, `quantities` e `prices` precisavam ser manipuladas juntas e em sincronia, mas nada no código impedia que ficassem fora de ordem ou com tamanhos diferentes.

## Como executar

```bash
mvn test
```

Requer Java 25 e Maven instalados.

## Refatorações aplicadas

**Extração de `Client`:** nome e email do cliente saíram de `Order` para uma classe própria. Além de reduzir a responsabilidade de `Order`, permite reutilizar `Client` em outras partes do sistema no futuro.

**Extração de `Item`:** as três listas paralelas foram substituídas por uma única `List<Item>`. Cada item carrega seu próprio produto, quantidade e preço, e calcula o próprio subtotal. Com isso, não existe mais a possibilidade de inconsistência entre as listas.

**Encapsulamento de `Order`:** todos os atributos passaram a `private final`. O único ponto de entrada para adicionar produtos é `addItem(Item)`. A taxa de desconto, que antes era pública e podia ser alterada de fora, agora é definida no construtor e não muda.

**Extração de métodos de cálculo:** o cálculo de subtotal e desconto saiu do meio de `printInvoice()` para métodos privados. O `printInvoice()` passou a apenas chamar esses métodos, tornando o fluxo mais fácil de seguir. O método `calculateTotal()` ficou público para permitir testes diretos.

**Remoção de `DiscountPolicy`:** a classe existia mas nunca era chamada. A lógica de desconto já estava em `Order` de forma mais coesa, então `DiscountPolicy` foi removida sem perda alguma.

**Javadoc em `EmailService`:** adicionado um aviso de que o serviço é chamado internamente por `Order` e não deve ser acessado diretamente pelo código cliente.

## Estrutura final

```
src/main/java/com/ecommerce/
  App.java           ponto de entrada da aplicação
  Client.java        dados do cliente (nome e email)
  Item.java          produto com quantidade, preço e subtotal
  Order.java         pedido com cálculo de total e emissão de fatura
  EmailService.java  envio de email (usado internamente por Order)

src/test/java/com/ecommerce/
  OrderTest.java     5 testes cobrindo cálculo de subtotal, desconto e total
```
