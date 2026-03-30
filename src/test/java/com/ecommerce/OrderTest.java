package com.ecommerce;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class OrderTest {

    private Order order;

    @BeforeEach
    void setUp() {
        Client client = new Client("João", "joao@email.com");
        order = new Order(client, 0.1);
    }

    @Test
    void subtotalWithSingleItem() {
        order.addItem(new Item("Notebook", 1, 3500.0));

        assertThat(order.calculateTotal()).isCloseTo(3150.0, within(0.01));
    }

    @Test
    void subtotalWithMultipleItems() {
        order.addItem(new Item("Notebook", 1, 3500.0));
        order.addItem(new Item("Mouse", 2, 80.0));

        // subtotal = 3660, desconto 10% = 366, total = 3294
        assertThat(order.calculateTotal()).isCloseTo(3294.0, within(0.01));
    }

    @Test
    void discountIsAppliedCorrectly() {
        order.addItem(new Item("Notebook", 1, 3500.0));
        order.addItem(new Item("Mouse", 2, 80.0));

        double total = order.calculateTotal();
        double expectedTotal = 3660.0 * 0.9;
        assertThat(total).isCloseTo(expectedTotal, within(0.01));
    }

    @Test
    void totalAfterDiscount() {
        order.addItem(new Item("Notebook", 1, 3500.0));
        order.addItem(new Item("Mouse", 2, 80.0));

        assertThat(order.calculateTotal()).isCloseTo(3294.0, within(0.01));
    }

    @Test
    void addItemIncreasesItemCount() {
        order.addItem(new Item("Notebook", 1, 3500.0));
        order.addItem(new Item("Mouse", 2, 80.0));

        assertThat(order.getItems()).hasSize(2);
    }
}
