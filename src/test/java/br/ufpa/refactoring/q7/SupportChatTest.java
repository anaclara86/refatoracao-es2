package br.ufpa.refactoring.q7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SupportChatTest {

    @Test
    void greeting_examples() {
        SupportChat chat = new SupportChat();

        assertEquals("Olá! Quer continuar como visitante?", chat.greeting(null));
        assertEquals("Conta bloqueada. Contate o suporte.",
                chat.greeting(new SupportChat.User("Ana", true)));
        assertEquals("Olá! Como posso ajudar?",
                chat.greeting(new SupportChat.User("  ", false)));
        assertEquals("Olá! Como posso ajudar?",
                chat.greeting(new SupportChat.User(null, false)));
        assertEquals("Olá, Victor! Como posso ajudar?",
                chat.greeting(new SupportChat.User("Victor", false)));
    }

    @Test
    void refactorGoal_introduceNullObject_removeNullCheck() {
        SupportChat chat = new SupportChat();

        // Validando comportamento com usuário não-nulo regular
        SupportChat.User user = new SupportChat.User("Carlos", false);
        assertEquals("Olá, Carlos! Como posso ajudar?", chat.greeting(user));

        // Validando comportamento com usuário bloqueado
        SupportChat.User bannedUser = new SupportChat.User("Banned", true);
        assertEquals("Conta bloqueada. Contate o suporte.", chat.greeting(bannedUser));
    }
}
