package tiago.modulefundamentalschallenge.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DivideServiceTest {

    @Test
    void test_getDivide() {
        DivideService divideService = new DivideService();

        Double response = divideService.getDivide(12.0, 3.0);

        assertNotNull(response);
        assertEquals(4.0, response);
    }

    @Test
    void test_getDivideByZero() {
        DivideService divideService = new DivideService();

        ArithmeticException arithmeticException = assertThrows(ArithmeticException.class,
                () -> divideService.getDivide(0.0, 0.0));
        assertEquals("Divide by zero is not permitted", arithmeticException.getMessage());
    }
}