package com.calculadora.model;

import com.calculadora.model.operation.IOperation;

public interface ICalc {

    public int calculation (IOperation operation, int value1, int value2);
}
