package com.ecommerce;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class ItemTest {

    @Test
    void subtotalWithSingleUnit() {
        Item item = new Item("Notebook", 1, 3500.0);

        assertThat(item.subtotal()).isCloseTo(3500.0, within(0.01));
    }

    @Test
    void subtotalWithMultipleUnits() {
        Item item = new Item("Mouse", 2, 80.0);

        assertThat(item.subtotal()).isCloseTo(160.0, within(0.01));
    }

    @Test
    void subtotalWithZeroQuantity() {
        Item item = new Item("Teclado", 0, 200.0);

        assertThat(item.subtotal()).isCloseTo(0.0, within(0.01));
    }

    @Test
    void storesFieldsCorrectly() {
        Item item = new Item("Monitor", 3, 1200.0);

        assertThat(item.getProductName()).isEqualTo("Monitor");
        assertThat(item.getQuantity()).isEqualTo(3);
        assertThat(item.getPrice()).isCloseTo(1200.0, within(0.01));
    }
}
