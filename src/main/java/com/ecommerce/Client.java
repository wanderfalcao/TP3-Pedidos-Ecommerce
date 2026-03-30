package com.ecommerce;

/**
 * Representa um cliente do sistema, agrupando nome e email
 * em um único objeto com acesso controlado.
 */
public class Client {
    private final String name;
    private final String email;

    public Client(String name, String email) {
        this.name = name;
        this.email = email;
    }

    /** Retorna o nome do cliente. */
    public String getName() {
        return name;
    }

    /** Retorna o email do cliente. */
    public String getEmail() {
        return email;
    }
}
