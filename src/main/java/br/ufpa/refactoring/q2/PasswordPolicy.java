package br.ufpa.refactoring.q2;

public class PasswordPolicy {

    public boolean isValid(String password) {
        return password != null && password.length() >= 10 && hasUpper(password) && hasDigit(password) && !password.contains(" ");
    }

    boolean hasUpper(String p) { return p.chars().anyMatch(Character::isUpperCase); }
    boolean hasDigit(String p) { return p.chars().anyMatch(Character::isDigit); }
}
