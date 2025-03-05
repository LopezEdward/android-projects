package dev.edwlopez.android.app5.data.entity;

import dev.edwlopez.android.app5.data.entity.interfaces.Client;

public class ClientEntity implements Client {
    private String name;
    private String email;
    private Integer telephoneNumber;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public Integer getTelephoneNumber() {
        return telephoneNumber;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setTelephoneNumber(Integer telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
}
