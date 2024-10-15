package com.calculadora.application;

import com.calculadora.controller.ControllerCalc;
import com.calculadora.dto.calcular.RequestDTO;
import com.calculadora.dto.calcular.ResponseDTO;
import com.calculadora.exception.OperacaoInvalidaException;
import com.calculadora.view.Menu;

public class Application {

    public static void main(String[] args) throws OperacaoInvalidaException {
        System.out.println(System.getProperty("user.dir"));
        ControllerCalc controllerCalc = new ControllerCalc();
        Menu menu = new Menu(controllerCalc);
        RequestDTO requestDTO = menu.show();
        ResponseDTO responseDTO = controllerCalc.calc(requestDTO);
        menu.showResult(responseDTO);

    }
}
