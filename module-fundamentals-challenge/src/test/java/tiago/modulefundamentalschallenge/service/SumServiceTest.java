package tiago.modulefundamentalschallenge.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class SumServiceTest {

    @Test
    void test_getSum() {
        SumService sumService = new SumService();

        Double response = sumService.getSum(10.0, 30.0);

        assertNotNull(response);
        assertEquals(40.0, response);
    }
}