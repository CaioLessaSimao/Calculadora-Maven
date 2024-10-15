package com.calculadora.view;

import com.calculadora.controller.ControllerCalc;
import com.calculadora.dto.calcular.RequestDTO;
import com.calculadora.dto.calcular.ResponseDTO;
import com.calculadora.dto.caminho.ResponsePathDTO;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    ArrayList<String> operacoes;


    public Menu(ControllerCalc controle) {
        //RequestPathDTO path = new RequestPathDTO();
        ResponsePathDTO operacoes = controle.geraMenu();
        this.operacoes = operacoes.getOperacoes();
    }

    public RequestDTO show(){
        this.showMenu();
        return this.captureValues();
    }

    private RequestDTO captureValues (){

        Scanner input = new Scanner(System.in);
        int opcao = input.nextInt();
        if(opcao == this.operacoes.size()+1) System.exit(0);

        System.out.println("Informe o primeiro valor:");
        int valor1 = input.nextInt();

        System.out.println("Informe o segundo valor:");
        int valor2 = input.nextInt();

        return new RequestDTO(this.operacoes.get(opcao-1),valor1,valor2);
    }

    private void showMenu(){

        System.out.println("Escolha sua operação: ");
        int index = 1;

        for(String op: this.operacoes){
            System.out.println(index + " - " + op);
            index++;
        }

        System.out.println(index + " - Sair ...");
    }

    public void showResult (ResponseDTO responseDTO){
        System.out.println("O Resultado é: "+responseDTO.getResult());
    }


}
