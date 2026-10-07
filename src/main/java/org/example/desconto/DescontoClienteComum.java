package org.example.desconto;

public class DescontoClienteComum implements CalculadoraDesconto {

    @Override
    public double calcular(double valor) {
        return valor * 0.05;
    }
}
