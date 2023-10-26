package com.teste.demo.services;

import org.springframework.stereotype.Service;

@Service
public class MathService {

    private double getDouble(String numberAsString) throws Exception{
        try{
            return Double.parseDouble(numberAsString);
        }
        catch (NumberFormatException | NullPointerException e){
            throw new UnsupportedOperationException("Set a numeric value");
        }
    }

    public Double sum(String numberOne, String numberTwo) throws Exception{
        return getDouble(numberOne) + getDouble(numberTwo);
    }


    public Double subtract(String numberOne, String numberTwo) throws Exception{
        return getDouble(numberOne) - getDouble(numberTwo);
    }


    public Double multiply(String numberOne, String numberTwo) throws Exception{
        return getDouble(numberOne) * getDouble(numberTwo);
    }


    public Double divide(String numberOne, String numberTwo) throws Exception{
        double number2 = getDouble(numberTwo);
        if(number2 == 0){
            throw new UnsupportedOperationException("Divisor can not be ZERO");
        }
        double number1 = getDouble(numberOne);
        return number1 / number2;
    }

    public Double sqrt(String number) throws Exception{

        double n = Double.parseDouble(number);
        if(n < 0){
            throw new UnsupportedOperationException("Undefined to negative values");
        }
        return Math.sqrt(n);

    }


    public Double media(String[] numbers) throws Exception{
        if(numbers.length == 0){
            throw new UnsupportedOperationException("Set a list os numeric values");
        }
        double sum = 0.0;
        for(String number : numbers){
            sum += getDouble(number);
        }
        return sum / numbers.length;
    }


}
