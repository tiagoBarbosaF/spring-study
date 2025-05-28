package tiago.modulefundamentalschallenge.service;

import org.springframework.stereotype.Service;

@Service
public class DivideService {

    public Double getDivide(Double number1, Double number2) {
        if(number2 == 0)
            throw new ArithmeticException("Divide by zero is not permitted");
        return number1 / number2;
    }
}
