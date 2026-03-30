package com.ecommerce;

import java.util.*;

public class Order {
    private Client client;
    public List products = new ArrayList<>();
    public List quantities = new ArrayList<>();
    public List prices = new ArrayList<>();
    public double discountRate = 0.1;

    public Order(Client client) {
        this.client = client;
    }

    public void printInvoice() {
        double total = 0;
        System.out.println("Cliente: " + client.getName());
        for (int i = 0; i < products.size(); i++) {
            System.out.println(quantities.get(i) + "x " + products.get(i) + " - R$" + prices.get(i));
            total += (double) prices.get(i) * (int) quantities.get(i);
        }
        System.out.println("Subtotal: R$" + total);
        System.out.println("Desconto: R$" + (total * discountRate));
        System.out.println("Total final: R$" + (total * (1 - discountRate)));
    }

    public void sendEmail() {
        EmailService.sendEmail(client.getEmail(), "Pedido recebido! Obrigado pela compra.");
    }
}
