package com.calculadora.controller;

import com.calculadora.dto.RequestDTO;
import com.calculadora.dto.ResponseDTO;
import com.calculadora.exception.OperacaoInvalidaException;
import com.calculadora.model.Calc;
import com.calculadora.model.ICalc;
import com.calculadora.model.operation.*;

import java.lang.reflect.InvocationTargetException;

public class ControllerCalc {



    public ResponseDTO calc(RequestDTO requestDTO) throws OperacaoInvalidaException {
        int result = 0;
        ICalc calc = new Calc();

        String pacoteBase = "com.calculadora.model.operation."+requestDTO.getOpcao();

        try {

            Class<?> classeOperacao = Class.forName(pacoteBase);
            IOperation operation = (IOperation) classeOperacao.getDeclaredConstructor().newInstance();
            result = calc.calculation(operation,requestDTO.getValor1(),requestDTO.getValor2());

            return new ResponseDTO(result);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            throw new OperacaoInvalidaException("Erro ao criar instância da operação: " + e.getMessage());
        }

    }
}

