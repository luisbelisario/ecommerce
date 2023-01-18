package br.com.alura.ecommerce;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var email = Math.random() + "@email.com";
        try (var orderDispatcher = new KafkaDispatcher<Order>(NewOrderMain.class.getSimpleName())) {
            try (var emailDispatcher = new KafkaDispatcher<String>(NewOrderMain.class.getSimpleName())) {
                for (var i = 0; i < 10; i++) {

                    var orderId = UUID.randomUUID().toString();
                    var amount = new BigDecimal(Math.random() * 5000 + 1);

                    var order = new Order(orderId, email, amount);
                    orderDispatcher.send("ECOMMERCE_NEW_ORDER", email, order);

                    var emailPhrase = "Thank you for your order! We are processing your order!";
                    emailDispatcher.send("ECOMMERCE_SEND_EMAIL", email, emailPhrase);
                }
            }
        }
    }

}
