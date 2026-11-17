package br.ufpa.refactoring.q7;

public class SupportChat {

    public String greeting(User user) {
        User safeUser = (user != null) ? user : new NullUser();
        return safeUser.greeting();
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

        public String greeting() {
            if (isBanned()) return "Conta bloqueada. Contate o suporte.";
            if (name == null || name.isBlank()) return "Olá! Como posso ajudar?";
            return "Olá, " + name + "! Como posso ajudar?";
        }
    }

    public static class NullUser extends User {
        public NullUser() {
            super("", false);
        }

        @Override
        public boolean isNull() {
            return true;
        }

        @Override
        public String greeting() {
            return "Olá! Quer continuar como visitante?";
        }
    }
}
