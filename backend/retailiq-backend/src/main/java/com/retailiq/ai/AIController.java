package com.retailiq.ai;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ai")
public class AIController {

    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/ask")
    public AIResponse ask(@RequestBody AIRequest request) {

        System.out.println("===== AI ENDPOINT HIT =====");

        String answer = aiService.askAI(request.getQuestion());

        return new AIResponse(answer);
    }
}