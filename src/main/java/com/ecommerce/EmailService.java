package com.ecommerce;

/**
 * Serviço responsável pelo envio de emails do sistema.
 * Chamado internamente por {@link Order} — não deve ser acessado diretamente pelo código cliente.
 */
public class EmailService {

    /**
     * Envia um email para o destinatário informado.
     *
     * @param to      endereço de destino
     * @param message corpo da mensagem
     */
    public void sendEmail(String to, String message) {
        System.out.println("Enviando e-mail para " + to + ": " + message);
    }
}
