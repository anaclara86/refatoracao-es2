package br.ufpa.refactoring.q10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomBookingTest {

    @Test
    void quote_examples() {
        RoomBooking rb = new RoomBooking();

        assertEquals(100.0, rb.quote("B", 1, "101", 8, 10, false, false, 10), 0.001);
        assertEquals((2*50.0 + 30 + 20) * 1.15 + 10, rb.quote("A", 3, "101", 8, 10, true, true, 50), 0.01);
        assertEquals((2*50.0) + 40, rb.quote("B", 1, "LAB", 8, 10, false, false, 10), 0.01);
    }

    @Test
    void refactorGoal_introduceParameterObject() {
        RoomBooking rb = new RoomBooking();

        // Apenas projetor: (2 * 50) + 30 = 130.0
        assertEquals(130.0, rb.quote("B", 1, "102", 14, 16, true, false, 20), 0.001);

        // Apenas som: (2 * 50) + 20 = 120.0
        assertEquals(120.0, rb.quote("B", 1, "102", 14, 16, false, true, 20), 0.001);

        // Mais de 40 participantes com acréscimo de 15%: 50 * 1.15 = 57.50
        assertEquals(57.50, rb.quote("C", 1, "201", 9, 10, false, false, 45), 0.001);
    }
}
