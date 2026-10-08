package org.example.observador;

import org.example.modelo.Pedido;

public class NotificadorEmail implements ObservadorPedido {

    @Override
    public void aoProcessarPedido(Pedido pedido, double total) {
        System.out.printf("E-mail enviado para %s: pedido %d confirmado, total R$ %.2f%n",
                pedido.getEmail(), pedido.getNumero(), total);
    }
}
