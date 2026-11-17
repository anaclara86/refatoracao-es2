package br.ufpa.refactoring.q3;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PayrollCalculatorTest {

    @Test
    void netSalary_examples() {
        PayrollCalculator calc = new PayrollCalculator();

        var e1 = new PayrollCalculator.Employee(
                2800,
                List.of(new PayrollCalculator.Overtime(10, 25)),
                true,
                120
        );
        double salary1 = calc.monthlyNetSalary(e1);
        assertEquals(2800 + (10*25) - ((2800 + (10*25))*0.12) - 120 + 450, salary1, 0.01);

        var e2 = new PayrollCalculator.Employee(
                7500,
                List.of(),
                false,
                200
        );
        double salary2 = calc.monthlyNetSalary(e2);
        assertEquals(7500 - (7500*0.18) - 200, salary2, 0.01);
    }

    @Test
    void refactorGoal_replaceTempsWithQueries() {
        PayrollCalculator calc = new PayrollCalculator();

        var e3 = new PayrollCalculator.Employee(
                2500,
                List.of(new PayrollCalculator.Overtime(10, 20)),
                true,
                100
        );
        double salary3 = calc.monthlyNetSalary(e3);
        assertEquals(2834.00, salary3, 0.01);

        var e4 = new PayrollCalculator.Employee(
                3000,
                List.of(),
                false,
                50
        );
        double salary4 = calc.monthlyNetSalary(e4);
        assertEquals(2710.00, salary4, 0.01);
    }
}
