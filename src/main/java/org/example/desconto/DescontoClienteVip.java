package org.example.desconto;

public class DescontoClienteVip implements CalculadoraDesconto {

    @Override
    public double calcular(double valor) {
        return valor * 0.15;
    }
}
