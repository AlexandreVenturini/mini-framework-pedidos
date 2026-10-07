package org.example.processador;

import org.example.desconto.CalculadoraDesconto;
import org.example.modelo.Pedido;

public class ProcessadorPedidoDigital extends ProcessadorPedido {

    public ProcessadorPedidoDigital(CalculadoraDesconto calculadoraDesconto) {
        super(calculadoraDesconto);
    }

    @Override
    protected void validar(Pedido pedido) {
        if (pedido.getEmail() == null || !pedido.getEmail().contains("@")) {
            throw new IllegalArgumentException("pedido digital precisa de um e-mail válido para a entrega");
        }
    }

    @Override
    protected double calcularTotal(Pedido pedido) {
        double valor = pedido.getValor();
        return valor - calculadoraDesconto.calcular(valor);
    }
}
