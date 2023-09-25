package com.bootcamp.queuemanager.util;

public enum Type {
    SUGESTAO("SUGESTAO"),
    ELOGIO("ELOGIO"),
    CRITICA("CRITICA");

    private final String valor;
    Type(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public String toValue(){
        return name().toUpperCase();
    }

    public static Type fromValor(String valor) {
        for (Type tipo : Type.values()) {
            if (tipo.valor == valor) return tipo;
        }
        throw new IllegalArgumentException("Valor + " + valor + " inválido para Tipo.");
    }
}
