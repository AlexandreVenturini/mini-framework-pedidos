package org.example.observador;

import org.example.modelo.Pedido;

public interface ObservadorPedido {
    void aoProcessarPedido(Pedido pedido, double total);
}
