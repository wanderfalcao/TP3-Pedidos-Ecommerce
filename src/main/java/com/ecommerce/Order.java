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

    /** Retorna o total final do pedido após aplicação do desconto. */
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

    /** Imprime a fatura completa do pedido no console. */
    public void printInvoice() {
        double subtotal = calculateSubtotal();
        double discount = calculateDiscount(subtotal);

        System.out.println("Cliente: " + client.getName());
        for (Item item : items) {
            System.out.println(item.getQuantity() + "x " + item.getProductName() + " - R$" + item.getPrice());
        }
        System.out.println("Subtotal: R$" + subtotal);
        System.out.println("Desconto: R$" + discount);
        System.out.println("Total final: R$" + (subtotal - discount));
    }

    /** Envia o email de confirmação do pedido para o cliente. */
    public void sendEmail() {
        EmailService.sendEmail(client.getEmail(), "Pedido recebido! Obrigado pela compra.");
    }
}
