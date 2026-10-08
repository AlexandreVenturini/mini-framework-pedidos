package org.example.observador;

import org.example.modelo.Pedido;

public class LogAuditoria implements ObservadorPedido {

    @Override
    public void aoProcessarPedido(Pedido pedido, double total) {
        System.out.printf("Auditoria: pedido %d do cliente %s registrado com total R$ %.2f%n",
                pedido.getNumero(), pedido.getCliente(), total);
    }
}
