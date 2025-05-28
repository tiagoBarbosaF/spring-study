package tiago.modulefundamentalschallenge.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubtractServiceTest {

    @Test
    void test_getSubtraction() {
        SubtractService subtractService = new SubtractService();

        Double response = subtractService.getSubtraction(15.0, 30.0);

        assertNotNull(response);
        assertEquals(-15.0, response);
    }
}