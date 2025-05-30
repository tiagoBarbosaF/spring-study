package tiago.modulefundamentals.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tiago.modulefundamentals.service.MessageService;

import java.util.Map;

@RestController
@RequestMapping("/message")
@Tag(name = "Messages")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public ResponseEntity<Map<String, String>> getMessages() {
        String response = this.messageService.getMessage();
        return ResponseEntity.ok(Map.of("message", response));
    }
}
