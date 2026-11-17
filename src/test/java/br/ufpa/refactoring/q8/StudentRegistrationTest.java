package br.ufpa.refactoring.q8;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentRegistrationTest {

    @Test
    void validity_examples() {
        StudentRegistration ok = new StudentRegistration("Juremilda", "n@x.com", "9199999999", "Belém", "66000-000");
        assertTrue(ok.isValid());

        StudentRegistration badZip = new StudentRegistration("Juremilda", "n@x.com", "9199999999", "Belém", "66000000");
        assertFalse(badZip.isValid());

        StudentRegistration badName = new StudentRegistration("  ", "n@x.com", "9199999999", "Belém", "66000-000");
        assertFalse(badName.isValid());

        StudentRegistration badEmail = new StudentRegistration("Juremilda", "invalidemail", "9199999999", "Belém", "66000-000");
        assertFalse(badEmail.isValid());

        StudentRegistration badPhone = new StudentRegistration("Juremilda", "n@x.com", "123", "Belém", "66000-000");
        assertFalse(badPhone.isValid());
    }

    @Test
    void formattedContact_example() {
        StudentRegistration s = new StudentRegistration("Max", "v@ufpa.br", "91988887777", "Belém", "66000-000");
        assertEquals("Max | v@ufpa.br | 91988887777 | Belém - 66000-000", s.formattedContact());
    }

    @Test
    void refactorGoal_extractClass_contactAndAddress() {
        // Valida casos adicionais de campos nulos
        StudentRegistration nullFields = new StudentRegistration(null, null, null, null, null);
        assertFalse(nullFields.isValid());

        StudentRegistration validWithLongPhone = new StudentRegistration("Carlos", "carlos@ufpa.br", "91987654321", "Ananindeua", "67000-000");
        assertTrue(validWithLongPhone.isValid());
        assertEquals("Carlos | carlos@ufpa.br | 91987654321 | Ananindeua - 67000-000", validWithLongPhone.formattedContact());

        // Valida uso direto das classes extraídas
        ContactInfo contact = new ContactInfo("aluno@ufpa.br", "91999998888");
        Address address = new Address("Belém", "66075-110");
        StudentRegistration reg = new StudentRegistration("Aluno", contact, address);

        assertTrue(contact.isValid());
        assertTrue(address.isValid());
        assertTrue(reg.isValid());
        assertEquals("Aluno | aluno@ufpa.br | 91999998888 | Belém - 66075-110", reg.formattedContact());
    }
}
