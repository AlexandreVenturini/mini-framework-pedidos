package org.example;

import org.example.desconto.DescontoClienteComum;
import org.example.desconto.DescontoClienteVip;
import org.example.modelo.Pedido;
import org.example.observador.BaixaEstoque;
import org.example.observador.LogAuditoria;
import org.example.observador.NotificadorEmail;
import org.example.processador.ProcessadorPedido;
import org.example.processador.ProcessadorPedidoDigital;
import org.example.processador.ProcessadorPedidoFisico;

public class Main {

    public static void main(String[] args) {
        NotificadorEmail email = new NotificadorEmail();
        BaixaEstoque estoque = new BaixaEstoque();
        LogAuditoria auditoria = new LogAuditoria();

        Pedido pedidoFisico = new Pedido.Builder()
                .numero(1)
                .cliente("Ana")
                .email("ana@email.com")
                .produto("Mouse sem fio")
                .quantidade(2)
                .precoUnitario(90.0)
                .enderecoEntrega("Rua das Flores, 123")
                .build();

        Pedido pedidoDigital = new Pedido.Builder()
                .numero(2)
                .cliente("Bruno")
                .email("bruno@email.com")
                .produto("Curso de Java")
                .quantidade(1)
                .precoUnitario(200.0)
                .build();

        System.out.println("=== Pedido físico | cliente comum | e-mail, estoque e auditoria ===");
        ProcessadorPedido fisicoComum = new ProcessadorPedidoFisico(new DescontoClienteComum());
        fisicoComum.registrar(email);
        fisicoComum.registrar(estoque);
        fisicoComum.registrar(auditoria);
        fisicoComum.processar(pedidoFisico);

        System.out.println("\n=== Pedido físico | cliente VIP | só auditoria ===");
        ProcessadorPedido fisicoVip = new ProcessadorPedidoFisico(new DescontoClienteVip());
        fisicoVip.registrar(auditoria);
        fisicoVip.processar(pedidoFisico);

        System.out.println("\n=== Pedido digital | cliente comum | e-mail e auditoria ===");
        ProcessadorPedido digitalComum = new ProcessadorPedidoDigital(new DescontoClienteComum());
        digitalComum.registrar(email);
        digitalComum.registrar(auditoria);
        digitalComum.processar(pedidoDigital);

        System.out.println("\n=== Pedido digital | cliente VIP | só e-mail ===");
        ProcessadorPedido digitalVip = new ProcessadorPedidoDigital(new DescontoClienteVip());
        digitalVip.registrar(email);
        digitalVip.processar(pedidoDigital);

        System.out.println("\n=== Pedido digital enviado ao processador físico ===");
        try {
            fisicoComum.processar(pedidoDigital);
        } catch (IllegalArgumentException e) {
            System.out.println("Pedido recusado: " + e.getMessage());
        }
    }
}
