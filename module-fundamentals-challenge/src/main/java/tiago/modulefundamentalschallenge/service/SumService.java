package tiago.modulefundamentalschallenge.service;

import org.springframework.stereotype.Service;

@Service
public class SumService {

    public Double getSum(Double number1, Double number2) {
        return number1 + number2;
    }
}
