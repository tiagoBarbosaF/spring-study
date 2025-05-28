package tiago.modulefundamentals.service;

import org.springframework.stereotype.Service;

@Service
public class MessageService {

    public String getMessage() {
        return "Testing Dependency Injection on Spring";
    }
}
