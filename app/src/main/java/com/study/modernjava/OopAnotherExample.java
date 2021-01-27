package com.study.modernjava;

public class OopAnotherExample {
    public static void main(String[] args) {
        // 여기까지는 노가다
        CalculatorService calculatorService = new CalculatorService();
        final int additionResult = calculatorService.calculate('+',5, 7);
        System.out.println(additionResult);
        final int subtractionResult = calculatorService.calculate('-',10, 5);
        System.out.println(subtractionResult);
        
        // 여기부터는 OOP이용
        CalculatorService1 calculatorService1 = new CalculatorService1(new Addition());
        System.out.println(calculatorService1.calculatee(5, 50));
    }
}

class CalculatorService {
    public int calculate(char calculation, int num1, int num2) {
        if (calculation == '+') {
            return num1 + num2;
        }
        if (calculation == '-') {
            return num1 - num2;
        }
        if (calculation == '*') {
            return num1 * num2;
        }
        if (calculation == '/') {
            return num1 / num2;
        }
        throw new IllegalArgumentException("Unknown calculation : " + calculation + "입력됨");
    }
}

interface Calculation {
    int calculate(int num1, int num2);
}
class Addition implements Calculation {
    @Override
    public int calculate(int num1, int num2) {
        return num1 + num2;
    }
}

class CalculatorService1 {
    private final Calculation calculation;

    CalculatorService1(Calculation calculation) {
        this.calculation = calculation;
    }

    public int calculatee(int num1, int num2) {
        return calculation.calculate(num1, num2);
    }
}

