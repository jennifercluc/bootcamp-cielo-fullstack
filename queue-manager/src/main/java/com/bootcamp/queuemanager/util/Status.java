package com.bootcamp.queuemanager.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
    RECEBIDO("RECEBIDO"),
    EM_PROCESSAMENTO("EM_PROCESSAMENTO"),
    FINALIZADO("FINALIZADO");

    private final String valor;
    Status(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    @JsonValue
    public String toValue(){
        return name().toUpperCase();
    }

    @JsonCreator
    public static Status fromValor(String valor) {
        for (Status status : Status.values()) {
            if (status.valor == valor) return status;
        }
        throw new IllegalArgumentException("Valor + " + valor + " inválido para Status.");
    }
}
