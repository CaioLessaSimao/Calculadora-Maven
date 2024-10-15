package com.calculadora.dto.caminho;

import java.util.ArrayList;

public class ResponsePathDTO {
    ArrayList<String> operacoes;

    public ResponsePathDTO(ArrayList<String> operacoes){
        this.operacoes = operacoes;
    }

    public ArrayList<String> getOperacoes() {
        return operacoes;
    }
}
