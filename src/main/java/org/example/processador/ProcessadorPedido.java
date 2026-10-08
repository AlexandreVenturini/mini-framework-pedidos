package org.example.processador;

import java.util.ArrayList;
import java.util.List;

import org.example.desconto.CalculadoraDesconto;
import org.example.modelo.Pedido;
import org.example.observador.ObservadorPedido;

public abstract class ProcessadorPedido {

    protected final CalculadoraDesconto calculadoraDesconto;
    private final List<ObservadorPedido> observadores = new ArrayList<>();

    protected ProcessadorPedido(CalculadoraDesconto calculadoraDesconto) {
        this.calculadoraDesconto = calculadoraDesconto;
    }

    public void registrar(ObservadorPedido observador) {
        observadores.add(observador);
    }

    public final void processar(Pedido pedido) {
        validar(pedido);
        System.out.println("Pedido " + pedido.getNumero() + " validado");

        double total = calcularTotal(pedido);
        System.out.printf("Total calculado: R$ %.2f%n", total);

        notificar(pedido, total);
    }

    protected abstract void validar(Pedido pedido);

    protected abstract double calcularTotal(Pedido pedido);

    private void notificar(Pedido pedido, double total) {
        observadores.forEach(observador -> observador.aoProcessarPedido(pedido, total));
    }
}
