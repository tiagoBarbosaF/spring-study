package tiago.modulefundamentalschallenge.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MultiplyServiceTest {

    @Test
    void test_getMultiply() {
        MultiplyService multiplyService = new MultiplyService();

        Double response = multiplyService.getMultiply(10.0, 15.0);

        assertNotNull(response);
        assertEquals(150.0, response);
    }
}