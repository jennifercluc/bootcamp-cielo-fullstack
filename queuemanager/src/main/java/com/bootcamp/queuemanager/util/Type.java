package com.bootcamp.queuemanager.util;

public enum Type {
    SUGESTAO('S'),
    ELOGIO('E'),
    CRITICA('C');

    private final Character valor;
    Type(Character valor) {
        this.valor = valor;
    }

    public Character getValor() {
        return valor;
    }

    public static Type fromValor(Character valor) {
        for (Type tipo : Type.values()) {
            if (tipo.valor == valor) return tipo;
        }
        throw new IllegalArgumentException("Valor + " + valor + " inválido para Tipo.");
    }
}
