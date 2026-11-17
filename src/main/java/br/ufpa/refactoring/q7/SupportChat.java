package br.ufpa.refactoring.q7;

public class SupportChat {

    public String greeting(User user) {
        if (user == null) return "Olá! Quer continuar como visitante?";
        if (user.isBanned()) return "Conta bloqueada. Contate o suporte.";
        if (user.name() == null || user.name().isBlank()) return "Olá! Como posso ajudar?";
        return "Olá, " + user.name() + "! Como posso ajudar?";
    }

    public static class User {
        private final String name;
        private final boolean banned;

        public User(String name, boolean banned) {
            this.name = name;
            this.banned = banned;
        }

        public String name() { return name; }
        public boolean banned() { return banned; }
        public boolean isBanned() { return banned; }
        public boolean isNull() { return false; }
    }
}
