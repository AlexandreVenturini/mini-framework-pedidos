# Mini-Framework de Processamento de Pedidos

Framework simples em Java que combina três padrões de projeto para processar pedidos.

| Padrão | Classe | Como foi aplicado |
|---|---|---|
| **Template Method** | `ProcessadorPedido` | `processar(Pedido)` é `final` e sempre executa `validar()` → `calcularTotal()` → `notificar()`. `validar()` e `calcularTotal()` são abstratos. |
| **Strategy** | `CalculadoraDesconto` | O desconto é injetado no processador pelo construtor: `DescontoClienteComum` (5%) e `DescontoClienteVip` (15%). |
| **Observer** | `ObservadorPedido` | `notificar()` percorre os observadores registrados: `NotificadorEmail`, `BaixaEstoque` e `LogAuditoria`. |

## Processadores

| | `ProcessadorPedidoFisico` | `ProcessadorPedidoDigital` |
|---|---|---|
| **Validação** | exige endereço de entrega | exige e-mail válido (é por onde o produto é entregue) |
| **Cálculo** | valor − desconto + frete de R$ 20,00 | valor − desconto (sem frete) |

## Estrutura

```
src/main/java/org/example
├── Main.java
├── modelo/        Pedido (com Builder)
├── desconto/      CalculadoraDesconto, DescontoClienteComum, DescontoClienteVip
├── observador/    ObservadorPedido, NotificadorEmail, BaixaEstoque, LogAuditoria
└── processador/   ProcessadorPedido, ProcessadorPedidoFisico, ProcessadorPedidoDigital
```

## Como executar

Abra o projeto no IntelliJ (Maven, JDK 26) e execute a classe `org.example.Main`.

O `Main` processa pedidos variando o processador, a estratégia de desconto e os observadores registrados. Em todos os casos a ordem é a mesma (validação → total → notificações), o que mostra que o esqueleto do algoritmo nunca muda:

```
=== Pedido físico | cliente comum | e-mail, estoque e auditoria ===
Pedido 1 validado
Total calculado: R$ 191,00
E-mail enviado para ana@email.com: pedido 1 confirmado, total R$ 191,00
Estoque: baixa de 2 unidade(s) de Mouse sem fio
Auditoria: pedido 1 do cliente Ana registrado com total R$ 191,00

=== Pedido físico | cliente VIP | só auditoria ===
Pedido 1 validado
Total calculado: R$ 173,00
Auditoria: pedido 1 do cliente Ana registrado com total R$ 173,00

=== Pedido digital | cliente comum | e-mail e auditoria ===
Pedido 2 validado
Total calculado: R$ 190,00
E-mail enviado para bruno@email.com: pedido 2 confirmado, total R$ 190,00
Auditoria: pedido 2 do cliente Bruno registrado com total R$ 190,00

=== Pedido digital | cliente VIP | só e-mail ===
Pedido 2 validado
Total calculado: R$ 170,00
E-mail enviado para bruno@email.com: pedido 2 confirmado, total R$ 170,00

=== Pedido digital enviado ao processador físico ===
Pedido recusado: pedido físico precisa de endereço de entrega
```

## Avaliação dos padrões criacionais

**Builder — aplicado em `Pedido`.**
O pedido tem sete atributos (número, cliente, e-mail, produto, quantidade, preço e endereço), e o endereço só existe no pedido físico. Com um construtor comum teríamos o problema do *telescoping constructor*: uma chamada longa, difícil de ler e com `null` no lugar do que não se aplica. Com o Builder cada atributo fica autoexplicativo, a ordem não importa, o endereço pode ser omitido no pedido digital e o objeto final é imutável.

**Factory Method — não aplicado.**
A Factory resolve o problema de ter `if/else` espalhado pelo sistema para decidir qual classe instanciar. Aqui os processadores são criados em um único lugar (`Main`), e escolher o processador, a estratégia e os observadores é justamente o que o exercício pede para variar de forma explícita. Ela passaria a valer a pena se o tipo do pedido viesse de fora (por exemplo, de um formulário ou de uma API). Nesse caso, uma `ProcessadorFactory` com `enum` e `switch` centralizaria essa decisão.

**Singleton — não aplicado.**
Nenhuma classe precisa garantir uma instância única com acesso global. Os observadores e as estratégias são passados para os processadores (injeção de dependência), o que facilita os testes. Quando uma mesma instância precisa ser compartilhada, como o `LogAuditoria`, basta criá-la uma vez e registrá-la em vários processadores.

**Conclusão:** só o Builder resolve uma dor concreta deste projeto. Aplicar os outros dois agora seria *over-engineering*.
