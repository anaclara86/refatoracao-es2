package br.ufpa.refactoring.q3;

import java.util.List;

public class PayrollCalculator {

    public double monthlyNetSalary(Employee e) {
        double net = calculateGross(e) - calculateTax(e) - e.healthPlanDeduction() + mealVoucher(e);
        return Math.round(net * 100.0) / 100.0;
    }

    double calculateGross(Employee e) {
        return e.baseSalary() + calculateOvertime(e);
    }

    double calculateOvertime(Employee e) {
        double overtime = 0.0;
        for (Overtime ot : e.overtimes()) overtime += ot.hours() * ot.hourRate();
        return overtime;
    }

    double calculateTax(Employee e) {
        double gross = calculateGross(e);
        if (gross <= 3000) return gross * 0.08;
        if (gross <= 7000) return gross * 0.12;
        return gross * 0.18;
    }

    double mealVoucher(Employee e) {
        return e.hasMealVoucher() ? 450.0 : 0.0;
    }

    public record Employee(double baseSalary, List<Overtime> overtimes,
                           boolean hasMealVoucher, double healthPlanDeduction) {}

    public record Overtime(int hours, double hourRate) {}
}
