package com.ecommerce;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ClientTest {

    @Test
    void storesNameAndEmail() {
        Client client = new Client("João", "joao@email.com");

        assertThat(client.getName()).isEqualTo("João");
        assertThat(client.getEmail()).isEqualTo("joao@email.com");
    }
}
