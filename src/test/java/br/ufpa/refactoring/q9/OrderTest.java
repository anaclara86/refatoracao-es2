package br.ufpa.refactoring.q9;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void finalTotal_examples() {
        var vip = new Order.Customer(true, 6);
        var o1 = new Order(vip, 200.0, 0.10);
        assertEquals(140.0, o1.finalTotal(), 0.001);

        var regular = new Order.Customer(false, 1);
        var o2 = new Order(regular, 200.0, 0.10);
        assertEquals(180.0, o2.finalTotal(), 0.001);
    }

    @Test
    void refactorGoal_moveDiscountLogic_toCustomerOrPolicy() {
        // VIP com menos de 5 anos: bônus de 25.0
        var vipNovo = new Order.Customer(true, 2);
        var o3 = new Order(vipNovo, 100.0, 0.0);
        assertEquals(75.0, o3.finalTotal(), 0.001);

        // Não-VIP com exatamente 5 anos de casa: bônus de 15.0
        var veterano = new Order.Customer(false, 5);
        var o4 = new Order(veterano, 100.0, 0.0);
        assertEquals(85.0, o4.finalTotal(), 0.001);

        // Total não pode ser negativo quando o desconto excede o valor
        var o5 = new Order(vipNovo, 20.0, 0.0);
        assertEquals(0.0, o5.finalTotal(), 0.001);
    }
}
