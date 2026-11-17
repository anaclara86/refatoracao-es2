package br.ufpa.refactoring.q1;

import java.time.LocalDate;
import java.util.List;

public class PurchaseEmailService {

    public String buildEmail(Purchase purchase) {
        StringBuilder sb = new StringBuilder();

        sb.append("Olá, ").append(purchase.customerName()).append("!\n\n");
        sb.append("Seu pedido #").append(purchase.id()).append(" foi confirmado em ")
          .append(LocalDate.now()).append(".\n\n");

        sb.append("Itens:\n");
        for (PurchaseItem it : purchase.items()) {
            double line = it.unitPrice() * it.quantity();
            sb.append("- ").append(it.name())
              .append(" (").append(it.quantity()).append("x) : R$ ")
              .append(String.format("%.2f", line)).append("\n");
        }

        double subtotal = calculateSubtotal(purchase);
        double shipping = calculateShipping(purchase);
        double discount = calculateDiscount(purchase, subtotal);
        double total = subtotal + shipping - discount;

        sb.append("\nSubtotal: R$ ").append(String.format("%.2f", subtotal)).append("\n");
        sb.append("Frete: R$ ").append(String.format("%.2f", shipping)).append("\n");
        sb.append("Descontos: R$ ").append(String.format("%.2f", discount)).append("\n");
        sb.append("TOTAL: R$ ").append(String.format("%.2f", total)).append("\n\n");

        sb.append(formatAddress(purchase.address()));

        sb.append("Obrigado por comprar com a gente!\n");
        return sb.toString();
    }

    private double calculateSubtotal(Purchase purchase) {
        double subtotal = 0.0;
        for (PurchaseItem it : purchase.items()) {
            subtotal += it.unitPrice() * it.quantity();
        }
        return subtotal;
    }

    private double calculateShipping(Purchase purchase) {
        return purchase.isExpress() ? 29.90 : 14.90;
    }

    private double calculateDiscount(Purchase purchase, double subtotal) {
        double discount = 0.0;
        if (subtotal >= 300.0) {
            discount = subtotal * 0.10;
        }
        if (purchase.coupon() != null && purchase.coupon().startsWith("VIP")) {
            discount += 20.0;
        }
        return discount;
    }

    private String formatAddress(Address address) {
        StringBuilder sb = new StringBuilder();
        sb.append("Endereço de entrega:\n");
        sb.append(address.street()).append(", ")
          .append(address.number()).append("\n");
        sb.append(address.city()).append(" - ")
          .append(address.zip()).append("\n\n");
        return sb.toString();
    }

    public record Purchase(String id, String customerName, List<PurchaseItem> items,
                           boolean isExpress, String coupon, Address address) {}

    public record PurchaseItem(String name, int quantity, double unitPrice) {}

    public record Address(String street, String number, String city, String zip) {}
}
