package org.example.observador;

import org.example.modelo.Pedido;

public class BaixaEstoque implements ObservadorPedido {

    @Override
    public void aoProcessarPedido(Pedido pedido, double total) {
        System.out.printf("Estoque: baixa de %d unidade(s) de %s%n",
                pedido.getQuantidade(), pedido.getProduto());
    }
}
