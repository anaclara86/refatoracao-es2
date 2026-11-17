package br.ufpa.refactoring.q8;

import java.util.regex.Pattern;

public class Address {

    private final String city;
    private final String zip;

    public Address(String city, String zip) {
        this.city = city;
        this.zip = zip;
    }

    public String city() {
        return city;
    }

    public String zip() {
        return zip;
    }

    public boolean isValid() {
        return zip != null && Pattern.matches("\\d{5}-\\d{3}", zip);
    }

    public String formatted() {
        return city + " - " + zip;
    }
}
