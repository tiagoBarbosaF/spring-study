package tiago.modulefundamentals.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import tiago.modulefundamentals.service.MessageService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MessageController.class)
@Import(MessageControllerTest.TestConfig.class)
class MessageControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MessageService messageService;

    @Test
    void test_getMessages() {
        String responseMessage = "Testing Dependency Injection on Spring";
        when(messageService.getMessage()).thenReturn(responseMessage);

        try {
            mockMvc.perform(get("/message"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.message").value(responseMessage));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        MessageService messageService() {
            return mock(MessageService.class);
        }
    }
}