package com.ecommerce;

/**
 * Representa um item de um pedido, agrupando produto, quantidade e preço unitário.
 */
public class Item {
    private final String productName;
    private final int quantity;
    private final double price;

    public Item(String productName, int quantity, double price) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    /** @return preço unitário multiplicado pela quantidade */
    public double subtotal() {
        return price * quantity;
    }
}
