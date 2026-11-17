package br.ufpa.refactoring.q5;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class NotificationTest {

    @Test
    void render_shouldContainSubjectAndBody() {
        Notification n = new VIPPasswordResetNotification("Joelma");
        String r = n.render();

        assertEquals("Redefinição de senha", n.subject());
        assertEquals("Olá Joelma, clique no link para redefinir.", n.body());
        assertTrue(r.contains("Redefinição de senha"));
        assertTrue(r.contains("Olá Joelma, clique no link para redefinir."));
        assertTrue(r.startsWith("[" + LocalDate.now() + "]"));
    }

    @Test
    void refactorGoal_collapseHierarchy_removeRedundantSubclass() {
        PasswordResetNotification prn = new PasswordResetNotification("Chimbinha");
        String r = prn.render();

        assertEquals("Redefinição de senha", prn.subject());
        assertEquals("Olá Chimbinha, clique no link para redefinir.", prn.body());
        assertTrue(r.contains("Redefinição de senha"));
        assertTrue(r.contains("Olá Chimbinha"));
    }
}
