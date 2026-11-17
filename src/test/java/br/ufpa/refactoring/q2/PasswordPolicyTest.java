package br.ufpa.refactoring.q2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordPolicyTest {

    @Test
    void validPassword_examples() {
        PasswordPolicy policy = new PasswordPolicy();
        assertTrue(policy.isValid("SenhaForte1"));
        assertTrue(policy.isValid("Abcdefgh10"));
        assertTrue(policy.isValid("Valida123456"));
    }

    @Test
    void invalidPassword_examples() {
        PasswordPolicy policy = new PasswordPolicy();
        assertFalse(policy.isValid("curta1A"));       // < 10
        assertFalse(policy.isValid("senhaforte1"));   // sem maiúscula
        assertFalse(policy.isValid("SenhaForteX"));   // sem dígito
        assertFalse(policy.isValid("Senha Forte1"));  // espaço
        assertFalse(policy.isValid(""));              // vazia
        assertFalse(policy.isValid(null));            // nulo
    }

    @Test
    void refactorGoal_inlineSomeHelpers() {
        PasswordPolicy policy = new PasswordPolicy();
        // Garante comportamento consistente para casos limites
        assertFalse(policy.isValid("1234567890"));    // apenas dígitos
        assertFalse(policy.isValid("ABCDEFGHIJ"));    // apenas maiúsculas
        assertFalse(policy.isValid("abcdefghij"));    // apenas minúsculas
        assertTrue(policy.isValid("Senha12345"));     // exatamente 10 caracteres válidos
    }
}
