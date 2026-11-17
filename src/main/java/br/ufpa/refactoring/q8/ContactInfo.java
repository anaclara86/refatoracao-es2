package br.ufpa.refactoring.q8;

public class ContactInfo {

    private final String email;
    private final String phone;

    public ContactInfo(String email, String phone) {
        this.email = email;
        this.phone = phone;
    }

    public String email() {
        return email;
    }

    public String phone() {
        return phone;
    }

    public boolean isValid() {
        if (email == null || !email.contains("@")) return false;
        if (phone == null || phone.length() < 10) return false;
        return true;
    }

    public String formatted() {
        return email + " | " + phone;
    }
}
