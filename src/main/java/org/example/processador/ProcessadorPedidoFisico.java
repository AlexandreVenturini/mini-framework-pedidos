package org.example.processador;

import org.example.desconto.CalculadoraDesconto;
import org.example.modelo.Pedido;

public class ProcessadorPedidoFisico extends ProcessadorPedido {

    private static final double FRETE = 20.0;

    public ProcessadorPedidoFisico(CalculadoraDesconto calculadoraDesconto) {
        super(calculadoraDesconto);
    }

    @Override
    protected void validar(Pedido pedido) {
        if (pedido.getEnderecoEntrega() == null || pedido.getEnderecoEntrega().isBlank()) {
            throw new IllegalArgumentException("pedido físico precisa de endereço de entrega");
        }
    }

    @Override
    protected double calcularTotal(Pedido pedido) {
        double valor = pedido.getValor();
        return valor - calculadoraDesconto.calcular(valor) + FRETE;
    }
}
