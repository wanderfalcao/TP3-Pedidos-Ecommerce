package com.ecommerce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private final Client client;
    private final List<Item> items = new ArrayList<>();
    private final double discountRate;

    public Order(Client client, double discountRate) {
        this.client = client;
        this.discountRate = discountRate;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void printInvoice() {
        double total = 0;
        System.out.println("Cliente: " + client.getName());
        for (Item item : items) {
            System.out.println(item.getQuantity() + "x " + item.getProductName() + " - R$" + item.getPrice());
            total += item.subtotal();
        }
        System.out.println("Subtotal: R$" + total);
        System.out.println("Desconto: R$" + (total * discountRate));
        System.out.println("Total final: R$" + (total * (1 - discountRate)));
    }

    public void sendEmail() {
        EmailService.sendEmail(client.getEmail(), "Pedido recebido! Obrigado pela compra.");
    }
}
