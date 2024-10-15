package com.calculadora.controller;

import com.calculadora.dto.calcular.RequestDTO;
import com.calculadora.dto.calcular.ResponseDTO;
import com.calculadora.dto.caminho.ResponsePathDTO;
import com.calculadora.exception.OperacaoInvalidaException;
import com.calculadora.model.Calc;
import com.calculadora.model.ICalc;
import com.calculadora.model.operation.*;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

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

    public ResponsePathDTO geraMenu(){
        ArrayList<String> operacoes = new ArrayList<>();
        try {
            //Caminho do diretorio
            Path dirpath = Paths.get("calculadora\\model\\operation");

            //Lista de arquivos do diretorio
            try(Stream<Path> filePaths = Files.list(dirpath)){
                //Iteração por cada arquivo
                filePaths.forEach(filePath -> {
                    try {
                        //Variavel que abriga o arquivo iterado
                        File file = filePath.toFile();

                        //Metodo Reflection para conseguir o nome do arquivo
                        Method getNameMethod = File.class.getMethod("getName");

                        //Utilização do método em si
                        String fileName = getNameMethod.invoke(file).toString();

                        //Tratamento da string
                        String[] nomeArquivo = fileName.split("\\.");

                        if(!fileName.equals("IOperation.java")){
                            operacoes.add(nomeArquivo[0]);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                return new ResponsePathDTO(operacoes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

