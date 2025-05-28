package tiago.modulefundamentalschallenge.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import tiago.modulefundamentalschallenge.service.DivideService;
import tiago.modulefundamentalschallenge.service.MultiplyService;
import tiago.modulefundamentalschallenge.service.SubtractService;
import tiago.modulefundamentalschallenge.service.SumService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CalculatorController.class)
@Import(CalculatorControllerTest.TestConfig.class)
class CalculatorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SumService sumService;

    @Autowired
    private SubtractService subtractService;

    @Autowired
    private MultiplyService multiplyService;

    @Autowired
    private DivideService divideService;

    @Test
    void test_getSum() throws Exception {
        when(sumService.getSum(10.0, 10.0)).thenReturn(20.0);

        mockMvc.perform(get("/calculator/sum")
                        .param("number1", "10.0")
                        .param("number2", "10.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response").value("20.0"));
    }

    @Test
    void test_getSubtract() throws Exception {
        when(subtractService.getSubtraction(15.0, 10.0)).thenReturn(5.0);

        mockMvc.perform(get("/calculator/subtract")
                        .param("number1", "15.0")
                        .param("number2", "10.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response").value("5.0"));
    }

    @Test
    void test_getMultiply() throws Exception {
        when(multiplyService.getMultiply(15.0, 10.0)).thenReturn(150.0);

        mockMvc.perform(get("/calculator/multiply")
                        .param("number1", "15.0")
                        .param("number2", "10.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response").value("150.0"));
    }

    @Test
    void test_getDivide() throws Exception {
        when(divideService.getDivide(12.0, 3.0)).thenReturn(4.0);

        mockMvc.perform(get("/calculator/divide")
                        .param("number1", "12.0")
                        .param("number2", "3.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response").value("4.0"));
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        SumService sumService() {
            return mock(SumService.class);
        }

        @Bean
        SubtractService subtractService() {
            return mock(SubtractService.class);
        }

        @Bean
        MultiplyService multiplyService() {
            return mock(MultiplyService.class);
        }

        @Bean
        DivideService divideService() {
            return mock(DivideService.class);
        }
    }
}