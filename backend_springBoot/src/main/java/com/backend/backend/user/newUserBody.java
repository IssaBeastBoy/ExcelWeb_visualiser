package com.backend.backend.user;

public class newUserBody {
    private String lasNumbr;
    private String name;
    private String surname;
    private String email;
    private String contact;
    private String password;

    public String getLasNumbr() {
        return lasNumbr;
    }

    public void setLasNumbr(String lasNumbr) {
        this.lasNumbr = lasNumbr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
