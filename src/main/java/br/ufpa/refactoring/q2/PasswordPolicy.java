package br.ufpa.refactoring.q2;

public class PasswordPolicy {

    public boolean isValid(String password) {
        return password != null
                && password.length() >= 10
                && password.chars().anyMatch(Character::isUpperCase)
                && hasDigit(password)
                && !password.contains(" ");
    }

    boolean hasDigit(String p) { return p.chars().anyMatch(Character::isDigit); }
}
