package br.com.alura.ecommerce;

import java.math.BigDecimal;

public class Order {

    private final String userId, orderId, userEmail;
    private final BigDecimal amount;

    public Order(String userId, String orderId, String userEmail, BigDecimal amount) {
        this.userId = userId;
        this.orderId = orderId;
        this.userEmail = userEmail;
        this.amount = amount;
    }

    public String getEmail() {
        return "email";
    }
}
