package com.retailiq.ai;

import com.retailiq.dto.DashboardResponse;
import com.retailiq.service.DashboardService;
import org.springframework.stereotype.Service;

@Service
public class AIService {

    private final DashboardService dashboardService;
    private final GroqClient geminiClient;

    public AIService(
            DashboardService dashboardService,
            GroqClient geminiClient) {

        this.dashboardService = dashboardService;
        this.geminiClient = geminiClient;

    }

    public String askAI(String question) {

        DashboardResponse dashboard =
                dashboardService.getDashboardStats();

        String prompt = """
You are an AI Business Advisor for RetailIQ.

Current business statistics:

Total Products: %d
Total Customers: %d
Total Orders: %d
Low Stock Products: %d
Total Revenue: %.2f
Inventory Value: %.2f

User Question:
%s

Answer like a senior retail business consultant.
Give practical business recommendations.
Keep the answer under 200 words.
"""
                .formatted(
                        dashboard.getTotalProducts(),
                        dashboard.getTotalCustomers(),
                        dashboard.getTotalOrders(),
                        dashboard.getLowStockProducts(),
                        dashboard.getTotalRevenue(),
                        dashboard.getInventoryValue(),
                        question
                );

        return geminiClient.askGemini(prompt);

    }

}