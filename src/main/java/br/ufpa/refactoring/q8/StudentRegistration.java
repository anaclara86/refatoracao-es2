package br.ufpa.refactoring.q8;

public class StudentRegistration {

    private String name;
    private ContactInfo contactInfo;
    private Address address;

    public StudentRegistration(String name, String email, String phone, String city, String zip) {
        this.name = name;
        this.contactInfo = new ContactInfo(email, phone);
        this.address = new Address(city, zip);
    }

    public StudentRegistration(String name, ContactInfo contactInfo, Address address) {
        this.name = name;
        this.contactInfo = contactInfo;
        this.address = address;
    }

    public boolean isValid() {
        if (name == null || name.isBlank()) return false;
        if (contactInfo == null || !contactInfo.isValid()) return false;
        if (address == null || !address.isValid()) return false;
        return true;
    }

    public String formattedContact() {
        return name + " | " + contactInfo.formatted() + " | " + address.formatted();
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public Address getAddress() {
        return address;
    }
}
