package br.ufpa.refactoring.q9;

public class Order {

    private final Customer customer;
    private final double total;
    private final double discountRate;

    public Order(Customer customer, double total, double discountRate) {
        this.customer = customer;
        this.total = total;
        this.discountRate = discountRate;
    }

    public double finalTotal() {
        double discount = total * discountRate + customer.loyaltyDiscount();
        return Math.max(0.0, total - discount);
    }

    public record Customer(boolean vip, int yearsAsCustomer) {
        public boolean isVip() { return vip; }

        public double loyaltyDiscount() {
            double discount = 0.0;
            if (isVip()) discount += 25.0;
            if (yearsAsCustomer() >= 5) discount += 15.0;
            return discount;
        }
    }
}
