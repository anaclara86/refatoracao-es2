package br.ufpa.refactoring.q6;

public class BillingService {

    public interface PlanPolicy {
        double calculatePrice(int activeUsers, boolean prioritySupport);
    }

    public enum Plan implements PlanPolicy {
        FREE {
            @Override
            public double calculatePrice(int activeUsers, boolean prioritySupport) {
                return 0.0;
            }
        },
        STUDENT {
            @Override
            public double calculatePrice(int activeUsers, boolean prioritySupport) {
                double price = 19.90;
                if (prioritySupport) price += 59.90;
                return price;
            }
        },
        PRO {
            @Override
            public double calculatePrice(int activeUsers, boolean prioritySupport) {
                double price = 49.90 + (activeUsers * 1.25);
                if (prioritySupport) price += 59.90;
                if (activeUsers > 200) price *= 0.95;
                return price;
            }
        },
        ENTERPRISE {
            @Override
            public double calculatePrice(int activeUsers, boolean prioritySupport) {
                double price = 199.90 + (activeUsers * 0.90);
                if (prioritySupport) price += 59.90;
                return price;
            }
        };
    }

    public double monthlyPrice(Plan plan, int activeUsers, boolean prioritySupport) {
        if (plan == null) throw new IllegalArgumentException("Plano não pode ser nulo");
        double price = plan.calculatePrice(activeUsers, prioritySupport);
        return Math.round(price * 100.0) / 100.0;
    }
}
