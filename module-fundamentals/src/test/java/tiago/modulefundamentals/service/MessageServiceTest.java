package tiago.modulefundamentals.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

    @Test
    void test_getMessage() {
        MessageService messageService = new MessageService();

        String response = messageService.getMessage();

        assertNotNull(response);
        assertEquals("Testing Dependency Injection on Spring", response);
    }
}