package com.bootcamp.queuemanager.util;

public enum Type {
    SUGESTAO('S'),
    ELOGIO('E'),
    CRITICA('C');

    private final char valor;
    Type(char valor) {
        this.valor = valor;
    }

    public char getValor() {
        return valor;
    }

    public static Type fromValor(char valor) {
        for (Type tipo : Type.values()) {
            if (tipo.valor == valor) return tipo;
        }
        throw new IllegalArgumentException("Valor + " + valor + " inválido para Tipo.");
    }
}
