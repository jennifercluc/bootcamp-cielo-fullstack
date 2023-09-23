package com.bootcamp.queuemanager.util;

public enum Status {
    RECEBIDO(1),
    PROCESSADO(2),
    FINALIZADO(3);

    private final int valor;
    Status(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public static Status fromValor(int valor) {
        for (Status status : Status.values()) {
            if (status.valor == valor) return status;
        }
        throw new IllegalArgumentException("Valor numérico + " + valor + " inválido para Status.");
    }
}
