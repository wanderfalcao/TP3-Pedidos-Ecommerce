package com.ecommerce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa um pedido, associando um cliente a uma lista de itens
 * e aplicando a taxa de desconto configurada.
 */
public class Order {
    private final Client client;
    private final List<Item> items = new ArrayList<>();
    private final double discountRate;
    private final EmailService emailService;

    public Order(Client client, double discountRate, EmailService emailService) {
        this.client = client;
        this.discountRate = discountRate;
        this.emailService = emailService;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    /** @return total do pedido após desconto */
    public double calculateTotal() {
        double subtotal = calculateSubtotal();
        return subtotal - calculateDiscount(subtotal);
    }

    private double calculateSubtotal() {
        return items.stream().mapToDouble(Item::subtotal).sum();
    }

    private double calculateDiscount(double subtotal) {
        return subtotal * discountRate;
    }

    public void printInvoice() {
        double subtotal = calculateSubtotal();
        double discount = calculateDiscount(subtotal);

        System.out.println("Cliente: " + client.getName());
        for (Item item : items) {
            System.out.println(item.toDisplayString());
        }
        System.out.println("Subtotal: R$" + subtotal);
        System.out.println("Desconto: R$" + discount);
        System.out.println("Total final: R$" + (subtotal - discount));
    }

    public void sendEmail() {
        emailService.sendEmail(client.getEmail(), "Pedido recebido! Obrigado pela compra.");
    }
}
