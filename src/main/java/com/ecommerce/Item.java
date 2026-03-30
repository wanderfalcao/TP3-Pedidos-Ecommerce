package com.ecommerce;

/**
 * Representa um item de um pedido, agrupando produto, quantidade e preço
 * unitário em um objeto coeso.
 */
public class Item {
    private final String productName;
    private final int quantity;
    private final double price;

    /**
     * @param productName nome do produto
     * @param quantity    quantidade comprada
     * @param price       preço unitário
     */
    public Item(String productName, int quantity, double price) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    /** Retorna o nome do produto. */
    public String getProductName() {
        return productName;
    }

    /** Retorna a quantidade. */
    public int getQuantity() {
        return quantity;
    }

    /** Retorna o preço unitário. */
    public double getPrice() {
        return price;
    }

    /**
     * Retorna o valor total deste item (preço * quantidade).
     *
     * @return subtotal do item
     */
    public double subtotal() {
        return price * quantity;
    }
}
