package tiago.modulefundamentalschallenge.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tiago.modulefundamentalschallenge.service.DivideService;
import tiago.modulefundamentalschallenge.service.MultiplyService;
import tiago.modulefundamentalschallenge.service.SubtractService;
import tiago.modulefundamentalschallenge.service.SumService;

import java.util.Map;

@RestController
@RequestMapping("/calculator")
@Tag(name = "Calculator")
public class CalculatorController {
    private final SumService sumService;
    private final SubtractService subtractService;
    private final MultiplyService multiplyService;
    private final DivideService divideService;

    public CalculatorController(SumService sumService,
                                SubtractService subtractService,
                                MultiplyService multiplyService,
                                DivideService divideService) {
        this.sumService = sumService;
        this.subtractService = subtractService;
        this.multiplyService = multiplyService;
        this.divideService = divideService;
    }

    @GetMapping("/sum")
    public ResponseEntity<Map<String, Double>> getSum(@RequestParam Double number1, @RequestParam Double number2) {
        Double response = this.sumService.getSum(number1, number2);
        return response != null
                ? ResponseEntity.status(HttpStatus.OK).body(Map.of("response", response))
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/subtract")
    public ResponseEntity<Map<String, Double>> getSubtract(@RequestParam Double number1, @RequestParam Double number2) {
        Double response = this.subtractService.getSubtraction(number1, number2);
        return response != null
                ? ResponseEntity.status(HttpStatus.OK).body(Map.of("response", response))
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/multiply")
    public ResponseEntity<Map<String, Double>> getMultiply(@RequestParam Double number1, @RequestParam Double number2) {
        Double response = this.multiplyService.getMultiply(number1, number2);
        return response != null
                ? ResponseEntity.status(HttpStatus.OK).body(Map.of("response", response))
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/divide")
    public ResponseEntity<Map<String, Double>> getDivide(@RequestParam Double number1, @RequestParam Double number2) {
        Double response = this.divideService.getDivide(number1, number2);
        return response != null
                ? ResponseEntity.status(HttpStatus.OK).body(Map.of("response", response))
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
