package com.ecommerce;

public class App {
    public static void main(String[] args) {
        Client client = new Client("João", "joao@email.com");
        Order order = new Order(client);
        order.products.add("Notebook");
        order.quantities.add(1);
        order.prices.add(3500.0);
        order.products.add("Mouse");
        order.quantities.add(2);
        order.prices.add(80.0);
        order.printInvoice();
        order.sendEmail();
    }
}
