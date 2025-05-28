package tiago.modulefundamentalschallenge.service;

import org.springframework.stereotype.Service;

@Service
public class MultiplyService {

    public Double getMultiply(Double number1, Double number2) {
        return number1 * number2;
    }
}
