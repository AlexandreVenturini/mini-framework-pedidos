package org.example.modelo;

public class Pedido {

    private final int numero;
    private final String cliente;
    private final String email;
    private final String produto;
    private final int quantidade;
    private final double precoUnitario;
    private final String enderecoEntrega;

    private Pedido(Builder builder) {
        this.numero = builder.numero;
        this.cliente = builder.cliente;
        this.email = builder.email;
        this.produto = builder.produto;
        this.quantidade = builder.quantidade;
        this.precoUnitario = builder.precoUnitario;
        this.enderecoEntrega = builder.enderecoEntrega;
    }

    public int getNumero() {
        return numero;
    }

    public String getCliente() {
        return cliente;
    }

    public String getEmail() {
        return email;
    }

    public String getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public double getValor() {
        return precoUnitario * quantidade;
    }

    public static class Builder {
        private int numero;
        private String cliente;
        private String email;
        private String produto;
        private int quantidade;
        private double precoUnitario;
        private String enderecoEntrega;

        public Builder numero(int numero) {
            this.numero = numero;
            return this;
        }

        public Builder cliente(String cliente) {
            this.cliente = cliente;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder produto(String produto) {
            this.produto = produto;
            return this;
        }

        public Builder quantidade(int quantidade) {
            this.quantidade = quantidade;
            return this;
        }

        public Builder precoUnitario(double precoUnitario) {
            this.precoUnitario = precoUnitario;
            return this;
        }

        public Builder enderecoEntrega(String enderecoEntrega) {
            this.enderecoEntrega = enderecoEntrega;
            return this;
        }

        public Pedido build() {
            return new Pedido(this);
        }
    }
}
