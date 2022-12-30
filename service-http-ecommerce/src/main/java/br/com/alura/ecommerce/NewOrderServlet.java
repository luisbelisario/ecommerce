package br.com.alura.ecommerce;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderServlet extends HttpServlet {

    private final KafkaDispatcher<Order> orderDispatcher = new KafkaDispatcher<>();

    private final KafkaDispatcher<String> emailDispatcher = new KafkaDispatcher<>();

    @Override
    public void destroy() {
        super.destroy();
        orderDispatcher.close();
        emailDispatcher.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            var email = req.getParameter("email");
            var orderId = UUID.randomUUID().toString();
            var amount = req.getParameter("amount");

            var order = new Order(orderId, email, new BigDecimal(amount));
            orderDispatcher.send("ECOMMERCE_NEW_ORDER", email, order);

            var emailPhrase = "Thank you for your order! We are processing your order!";
            emailDispatcher.send("ECOMMERCE_SEND_EMAIL", email, emailPhrase);

            System.out.println("New order processed!");
            resp.getWriter().println("New order processed!");
            resp.setStatus(200);

        } catch (ExecutionException | InterruptedException e) {
            throw new ServletException(e);
        }
    }
}
