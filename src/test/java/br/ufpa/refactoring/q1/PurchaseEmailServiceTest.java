package br.ufpa.refactoring.q1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseEmailServiceTest {

    @BeforeAll
    static void forceLocale() {
        Locale.setDefault(Locale.US);
    }

    @Test
    void buildEmail_shouldKeepSameOutputAfterRefactor() {
        PurchaseEmailService svc = new PurchaseEmailService();

        var purchase = new PurchaseEmailService.Purchase(
                "123",
                "Victor",
                List.of(
                        new PurchaseEmailService.PurchaseItem("Caderno", 2, 15.0),
                        new PurchaseEmailService.PurchaseItem("Caneta", 3, 3.5)
                ),
                false,
                "VIP-20",
                new PurchaseEmailService.Address("Rua A", "10", "Belém", "66000-000")
        );

        String email = svc.buildEmail(purchase);

        assertTrue(email.contains("Olá, Victor!"));
        assertTrue(email.contains("Seu pedido #123 foi confirmado"));
        assertTrue(email.contains("Itens:"));
        assertTrue(email.contains("- Caderno (2x) : R$ 30.00"));
        assertTrue(email.contains("- Caneta (3x) : R$ 10.50"));
        assertTrue(email.contains("Subtotal: R$ 40.50"));
        assertTrue(email.contains("Frete: R$ 14.90"));
        assertTrue(email.contains("Descontos: R$ 20.00"));
        assertTrue(email.contains("TOTAL: R$ 35.40"));
        assertTrue(email.contains("Endereço de entrega:"));
        assertTrue(email.contains("Rua A, 10"));
        assertTrue(email.contains("Belém - 66000-000"));
        assertTrue(email.contains("Obrigado por comprar com a gente!"));
    }

    @Test
    void buildEmail_withExpressAndSubtotalOver300_shouldApplyPercentageDiscountAndExpressShipping() {
        PurchaseEmailService svc = new PurchaseEmailService();

        var purchase = new PurchaseEmailService.Purchase(
                "456",
                "Ana",
                List.of(
                        new PurchaseEmailService.PurchaseItem("Livro Técnico", 3, 100.0),
                        new PurchaseEmailService.PurchaseItem("Mochila", 1, 100.0)
                ),
                true,
                "VIP-10",
                new PurchaseEmailService.Address("Av. Paulista", "1000", "São Paulo", "01310-100")
        );

        String email = svc.buildEmail(purchase);

        // Subtotal = 400.00
        // Shipping express = 29.90
        // Discount = 400 * 0.10 (40.0) + 20.0 (VIP) = 60.00
        // Total = 400.00 + 29.90 - 60.00 = 369.90
        assertTrue(email.contains("Subtotal: R$ 400.00"));
        assertTrue(email.contains("Frete: R$ 29.90"));
        assertTrue(email.contains("Descontos: R$ 60.00"));
        assertTrue(email.contains("TOTAL: R$ 369.90"));
    }
}
