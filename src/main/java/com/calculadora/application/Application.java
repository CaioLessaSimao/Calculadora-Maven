package com.calculadora.application;

import com.calculadora.controller.ControllerCalc;
import com.calculadora.dto.RequestDTO;
import com.calculadora.dto.ResponseDTO;
import com.calculadora.exception.OperacaoInvalidaException;
import com.calculadora.view.Menu;

public class Application {

    public static void main(String[] args) throws OperacaoInvalidaException {
        System.out.println(System.getProperty("user.dir"));
        Menu menu = new Menu();
        RequestDTO requestDTO = menu.show();
        ControllerCalc controllerCalc = new ControllerCalc();
        ResponseDTO responseDTO = controllerCalc.calc(requestDTO);
        menu.showResult(responseDTO);

    }
}
